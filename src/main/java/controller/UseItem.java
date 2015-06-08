package controller;

import dto.DTOGame;
import dto.DTOTurn;
import model.*;

/**
 * This class contains the effects of all the types of ItemCard; ItemCards can
 * be used only by the humans
 * 
 * @author Filippo
 *
 */
public class UseItem implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;

	public UseItem(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	public void teleport() {
		discard(ItemCardType.TELEPORT);
		Coordinate humanSector = gameStatus.getGame().getMap().getHumanCoord();
		gameStatus.getGame().getMap().getSector(humanSector).addPlayer(
						gameStatus.getPlayerPlay().getSector().removePlayer());
		dtoGame.setCoordinate(humanSector, gameStatus.getPlayerPlay().getNumber());
	}

	public void sedatives() {
		discard(ItemCardType.SEDATIVES);
		gameStatus.setSolveSectorDuty(true); 		//come se avessi pescato la carta settore pericoloso
	}

	public void spotlight(Sector chosen) {
		discard(ItemCardType.SPOTLIGHT);
		for (int i = 0; i < chosen.getPlayers().size(); i++) {
			Player declaring = chosen.getPlayers().get(i);
			dtoGame.setCoordinate(chosen.getCoordinate(), declaring.getNumber());
		}
		for (int i = 0; i < 6; i++) {
			Sector lighted = gameStatus.getGame().getMap().getSector(chosen.getAdjacent().get(i));
			if (lighted != null)
				for (int j = 0; j < lighted.getPlayers().size(); j++) {
					Player declaring = lighted.getPlayers().get(j);
					dtoGame.setCoordinate(lighted.getCoordinate(), declaring.getNumber());
				}
		}
	}

	public void adrenaline() {
		discard(ItemCardType.ADRENALINE);
		gameStatus.getPlayerPlay().setSpeed(2);
	}

	public void attack() {
		discard(ItemCardType.ATTACK);
		this.dtoGame=new Attack(gameStatus).attackMove();
	}

	private void discard(ItemCardType type) {
		for (int i = 0; i < gameStatus.getPlayerPlay().getItem().size(); i++) {
			if (gameStatus.getPlayerPlay().getItem().get(i).getType()
					.equals(type)) {
				gameStatus.getGame().getItemCards()
						.discard(gameStatus.getPlayerPlay().removeItem(i));
			}
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		boolean useCard=false;
		if (!gameStatus.isAttack() && gameStatus.isMove()&& !gameStatus.isSolveSectorDuty()
				&& dtoTurn.getTypeCard() == ItemCardType.ATTACK) {
			gameStatus.setAttack(true);
			useCard=true;
			attack();
		}
		if (dtoTurn.getTypeCard() == ItemCardType.SPOTLIGHT
				&& dtoTurn.getCoordinate() != null) {
			spotlight(gameStatus.getGame().getMap().getSector(dtoTurn.getCoordinate()));
			useCard=true;
		}
		if (dtoTurn.getTypeCard() == ItemCardType.SEDATIVES) {
			sedatives();
			useCard=true;
		}
		if (dtoTurn.getTypeCard() == ItemCardType.ADRENALINE) {
			adrenaline();
			useCard=true;
		}
		if (dtoTurn.getTypeCard() == ItemCardType.TELEPORT) {
			teleport();
			useCard=true;
		}

		if(useCard) {
			dtoGame.setDestination(9);
			dtoGame.setTypeItemCard(dtoTurn.getTypeCard());
		}
		else {
			dtoGame.setGameMessage("Non puoi usare questo oggetto in questo momento");
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
		}
		return dtoGame;
	}
}
