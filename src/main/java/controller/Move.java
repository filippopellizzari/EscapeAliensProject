package controller;

import dto.DTOGame;
import dto.DTOTurn;
import model.*;

/**
 * This class contains the control of the move and the effective action of move
 * 
 * @author Filippo
 *
 */
public class Move implements ChooseAnAction {

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus
	 *            , the status of a turn, reference at model and the player who
	 *            are playing, now is his turn
	 */

	public Move(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * check the validity of move
	 * 
	 * @param dest
	 * @return
	 */

	public boolean moveCheck(Coordinate dest) {

		Player player = status.getPlayer();
		Sector destSector = status.getGame().getMap().getSector(dest);
		if(destSector.isClosed()) 	//controlli se il personaggio può effettivamente andare nel settore
			return false;
		if(player.getType()==PlayerType.ALIEN && destSector.getType()==SectorType.HATCH)
			return false;
		return pathCheck(player.getSector().getCoordinate(), dest,
				player.getSpeed())
				&& destCheck(player, destSector);
	}

	/**
	 * 
	 * check that an alien can not move to an escape hatch sector
	 * 
	 * @param dest
	 * @return
	 */

	private boolean destCheck(Player player, Sector destSector) {
		return !(player.getType().equals(PlayerType.ALIEN) && destSector
				.getType().equals(SectorType.HATCH));
	}

	/**
	 * 
	 * Check that the destination is reached according to the speed player and
	 * that the crossed sectors are valid; it's a classic algorithm of Depth
	 * Search
	 * 
	 * @param curr
	 *            , starting coordinate
	 * @param dest
	 *            , destination coordinate
	 * @param speed
	 *            , speed of player
	 * @return
	 */

	private boolean pathCheck(Coordinate curr, Coordinate dest, int speed) {
		if (speed == 0) {
			return curr.equals(dest);
		} else {
			Sector currSector = status.getGame().getMap().getSector(curr);
			for (int i = 0; i < currSector.getAdjacent().size(); i++) {
				Coordinate adjCoord = currSector.getAdjacent().get(i);
				if(!status.getGame().getMap().isNull(adjCoord)){
					Sector adjSector = status.getGame().getMap()
							.getSector(adjCoord);
						speed--;
						if(adjSector.getCoordinate().equals(dest))
							return true;
						if (pathCheck(adjCoord, dest, speed)) {
							return true;
						}
						speed++;
					}

				}
			}
		return false;
	}

	/**
	 * this is the effective move
	 * 
	 * 
	 * @param destCoord
	 *            , coordinates chosen by player
	 * @return string, describes destination sector
	 */

	public void move(Coordinate destCoord) {
		Player player = status.getPlayer();
		Sector destSector = status.getGame().getMap().getSector(destCoord);
		destSector.addPlayer(player.getSector().removePlayer());
		player.setSector(destSector);
		// nel caso un umano avesse preso adrenalina
		if (player.getType().equals(PlayerType.HUMAN)) {
			player.setSpeed(1);
		}
		// mossa avvenuta con successo
		dtoGame.setCoordinate(destCoord, player.getNumber());
		dtoGame.setActionType(ActionType.MOVE);
		switch (destSector.getType()) {
		case DANGEROUS:
			if (!status.isSedated()) {
				// obbligato a pescare, se non è sedato
				status.setMustDraw(true);
			}
			dtoGame.setReceiver(status.getPlayer().getNumber());
			break;
		case SECURE:
			dtoGame.setReceiver(status.getPlayer().getNumber());
			break;
		case HATCH:
			drawHatchCard();
			// mostro a tutti carta hatch e posizione giocatore
			dtoGame.setReceiver(9);
			break;
		default:
			break;
		}
	}

	/**
	 * If player is on a HatchSector, a HatchCard is draw and solved, everyone
	 * is advised by the result
	 */

	public void drawHatchCard() {
		// pesco carta hatch
		HatchCard current = status.getGame().getHatchCards().draw();
		HatchCardColor color = current.getColor();
		dtoGame.setHatchCardColor(color);
		switch (color) {
		case RED:
			break;
		case GREEN:
			Player player = status.getPlayer();
			player.setInGame(false);
			player.setPlayerState(PlayerState.WINNER);
			removeAllItems(player);// partita conclusa per lui
			break;
		}
		status.getGame().getHatchCards().discard(current);
	}
	
	private void removeAllItems(Player player) {
		int numItems = player.getItem().size();
		for (int i = 0; i < numItems; i++) {
			status.getGame().getItemCards().discard(player.removeItem(0));
		}
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (!status.isMoved()) { // controllo stato del turno
			// controllo se la mossa è valida
			if (moveCheck(dtoTurn.getCoordinate())) {
				move(dtoTurn.getCoordinate());
				status.setMoved(true);
			} else {
				dtoGame.setGameMessage("Non puoi muovere in quel settore");
				dtoGame.setReceiver(status.getPlayer().getNumber());
			}
		} else {
			dtoGame.setGameMessage("Non puoi muovere adesso");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}

		return dtoGame;
	}

}
