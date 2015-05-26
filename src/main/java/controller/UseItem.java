package controller;

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

	public UseItem(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String teleport() {
		discard(ItemCardType.TELEPORT);
		String s = gameStatus.getPlayerPlay()
				+ "sta usando una carta oggetto\n";
		Coordinate humanSector = gameStatus.getGame().getMap().getHumanCoord();
		gameStatus
				.getGame()
				.getMap()
				.getSector(humanSector)
				.addPlayer(
						gameStatus.getPlayerPlay().getSector().removePlayer());
		return s;
	}

	public String sedatives() {
		discard(ItemCardType.SEDATIVES);
		String s = gameStatus.getPlayerPlay()
				+ " sta usando una carta oggetto\n";
		gameStatus.getPlayerPlay().setSedated(true);
		return s;
	}

	public String spotlight(Sector chosen) {
		discard(ItemCardType.SPOTLIGHT);
		String s = gameStatus.getPlayerPlay()
				+ " sta usando una carta oggetto\n";

		for (int i = 0; i < chosen.getPlayers().size(); i++) {
			Player declaring = chosen.getPlayers().get(i);
			s += declaring + " in sector " + chosen.getCoordinate() + "\n";

		}

		for (int i = 0; i < 6; i++) {
			Sector lighted = gameStatus.getGame().getMap()
					.getSector(chosen.getAdjacent().get(i));
			if (lighted != null)
				for (int j = 0; j < lighted.getPlayers().size(); j++) {
					Player declaring = lighted.getPlayers().get(j);
					s += declaring + " in sector " + lighted.getCoordinate()
							+ "\n";
				}
		}
		return s;
	}

	public String adrenaline() {
		discard(ItemCardType.ADRENALINE);
		String s = gameStatus.getPlayerPlay()
				+ " sta usando una carta oggetto\n";
		gameStatus.getPlayerPlay().setSpeed(2);
		return s;
	}

	public String attack() {
		discard(ItemCardType.ATTACK);
		String s = gameStatus.getPlayerPlay()
				+ " sta usando una carta oggetto\n";
		s += new Attack(gameStatus).attackMove();
		return s;
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
	public String doAction(DTOTurn dtoTurn) {
		String response;
		if (dtoTurn.getTypeCard() == ItemCardType.SPOTLIGHT
				&& dtoTurn.getCoordinate() != null) {
			response = spotlight(gameStatus.getGame().getMap()
					.getSector(dtoTurn.getCoordinate()));
		}
		if (dtoTurn.getTypeCard() == ItemCardType.SEDATIVES) {
			response = sedatives();
		}
		if (dtoTurn.getTypeCard() == ItemCardType.ADRENALINE) {
			response = adrenaline();
		}
		if (dtoTurn.getTypeCard() == ItemCardType.TELEPORT) {
			response = teleport();
		}
		if (!gameStatus.isAttack() && gameStatus.isMove()
				&& !gameStatus.isSolveSectorDuty()
				&& dtoTurn.getTypeCard() == ItemCardType.ATTACK)
			;
		{
			gameStatus.setAttack(true);
			response = attack();
		}
		return response;
	}
}