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
public class UseItem implements ChooseAnAction {

	private final GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus
	 *            the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public UseItem(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * Use the teleport no secondary effects
	 */

	public void teleport() {
		Player player = status.getPlayer();
		Coordinate humanCoord = status.getGame().getMap().getHumanCoord();
		Sector humanSector = status.getGame().getMap().getSector(humanCoord);
		humanSector.addPlayer(player.getSector().removePlayer());
		player.setSector(humanSector);
	}

	/**
	 * Use sedatives; player is sedated for all the turn; if he was in a
	 * dangerous sector, he is not more obliged to draw a dangerous sector card
	 * 
	 */

	public void sedatives() {
		status.setSedated(true);
		status.setMustDraw(false);
	}

	/**
	 * use spotlight; any player(included user) in the chosen sector must
	 * immediately announce his position
	 * 
	 * @param coord
	 *            , the coordinate of the chosen sector
	 */
	public void spotlight(Coordinate coord) {
		Sector chosen = status.getGame().getMap().getSector(coord);
		// notifica posizione giocatori nel settore scelto
		lightChosen(chosen);
		// notifica posizione giocatori nei settori attorno a quello scelto
		lightAdjacent(chosen);
	}
	
	private void lightChosen(Sector chosen){
		for (int i = 0; i < chosen.getPlayers().size(); i++) {
			Player declaring = chosen.getPlayers().get(i);
			dtoGame.setCoordinate(chosen.getCoordinate(), declaring.getNumber());
		}
	}
	private void lightAdjacent(Sector chosen){
		for (int i = 0; i < chosen.getAdjacent().size(); i++) {
			Coordinate adjCoord = chosen.getAdjacent().get(i);
			if (!status.getGame().getMap().isNull(adjCoord)){
				Sector lighted = status.getGame().getMap().getSector(adjCoord);
				for (int j = 0; j < lighted.getPlayers().size(); j++) {
					Player declaring = lighted.getPlayers().get(j);
					dtoGame.setCoordinate(lighted.getCoordinate(),
							declaring.getNumber());
				}
			}
		}
	}
	

	/**
	 * Use the adrenaline no secondary effects
	 */

	public void adrenaline() {
		status.getPlayer().setSpeed(2);
	}

	/**
	 * Use the same methods of alien to attack
	 */

	public void attack() {
		this.dtoGame = new Attack(status).attackMove();
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (!status.isAttacked() && status.isMoved()
				&& dtoTurn.getTypeCard() == ItemCardType.ATTACK) {
			attack();
			status.setAttacked(true);
			status.setMustDraw(false);
		} else {
			dtoGame.setGameMessage("Non puoi usare questo oggetto in questo momento");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}

		if (dtoTurn.getTypeCard().equals(ItemCardType.TELEPORT)) {
			teleport();
		}
		if (dtoTurn.getTypeCard().equals(ItemCardType.SEDATIVES)) {
			sedatives();
		}
		if (dtoTurn.getTypeCard().equals(ItemCardType.SPOTLIGHT)) {
			spotlight(dtoTurn.getCoordinate());
		}
		if (dtoTurn.getTypeCard().equals(ItemCardType.ADRENALINE)) {
			adrenaline();
		}
		
		// scarto la carta oggetto usata(qualunque sia)
		new DiscardItem(status).discardItem(dtoTurn.getTypeCard());
		// se aveva 4 carte, doveva scartarne una o usarla
		// in questo caso la usa, quindi l'obbligo non c'è più
		status.setMustDiscardItem(false);
		dtoGame.setReceiver(9);
		dtoGame.setItemCardType(dtoTurn.getTypeCard());

		return dtoGame;
	}

	public DTOGame getDtoGame() {
		return dtoGame;
	}
	
	
	
	
}
