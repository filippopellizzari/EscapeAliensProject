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
public class Move implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;

	public Move(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	/**
	 * check the validity of move
	 * 
	 * @param dest
	 * @return
	 */

	public boolean moveCheck(Coordinate dest) {

		return pathCheck(
				gameStatus.getPlayerPlay().getSector().getCoordinate(), dest,
				gameStatus.getPlayerPlay().getSpeed())
				&& destCheck(dest);
	}

	/**
	 * check that the destination is within the map ; in the case of aliens ,
	 * checks that can not access an escape hatch sector
	 * 
	 * @param dest
	 * @return
	 */

	private boolean destCheck(Coordinate dest) {
		if (!gameStatus.getGame().getMap().isNull(dest)) {
			if (gameStatus.getPlayerPlay().getType() == PlayerType.ALIEN
					&& gameStatus.getGame().getMap().getSector(dest).getType()
							.equals(SectorType.HATCH)) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * Check that the destination is reached according to the speed player and
	 * that the crossed sectors are valid
	 * 
	 * @param curr
	 *            starting coordinate
	 * @param dest
	 *            destination coordinate
	 * @param speed
	 *            speed of player
	 * @return
	 */

	private boolean pathCheck(Coordinate curr, Coordinate dest, int speed) {
		if (speed == 0) {
			return curr.equals(dest);
		} else {
			Sector currSector = gameStatus.getGame().getMap().getSector(curr);
			for (int i = 0; i < currSector.getAdjacent().size(); i++) {
				Coordinate adjCoord = currSector.getAdjacent().get(i);

				if (adjCoord.getX() != -1 && adjCoord.getY() != -1) {
					Sector adjSector = gameStatus.getGame().getMap()
							.getSector(adjCoord);
					if (!adjSector.isClosed()) {
						speed--;
						if (pathCheck(adjCoord, dest, speed)) {
							return true;
						}
						speed++;
					}

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
		Sector destSector = gameStatus.getGame().getMap().getSector(destCoord);
		destSector.addPlayer(gameStatus.getPlayerPlay().getSector()
				.removePlayer());
		gameStatus.getPlayerPlay().setSector(destSector);
		if(gameStatus.getGame().getPlayers(gameStatus.getPlayerPlay().getNumber()).getType()==PlayerType.HUMAN)
			gameStatus.getPlayerPlay().setSpeed(1);
		dtoGame.setCoordinate(destCoord, gameStatus.getPlayerPlay().getNumber());
		switch (destSector.getType()) {
		case DANGEROUS: case SECURE:
			break;
		case HATCH:
			drawHatchCard();
			break;
		default:
			break;
		}
	}
	
	public void drawHatchCard(){
		HatchCard current = gameStatus.getGame().getHatchCards().draw();
		HatchCardColor color = current.getColor();
		dtoGame.setHatchCardColor(color);
		dtoGame.setDestination(9);
		switch(color){
		  	case RED :
		  		break;
		  	case GREEN :
		  		gameStatus.getGame().getPlayers(gameStatus.getPlayerPlay().getNumber()).setAlive(false); //partita conclusa per lui
		  		break;
		}
		gameStatus.getGame().getHatchCards().discard(current);
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() == false && dtoTurn.getTypeCard() == null
				&& dtoTurn.getCoordinate() != null) { // mossa
			if (moveCheck(dtoTurn.getCoordinate())) {
				gameStatus.setMove(true);
				move(dtoTurn.getCoordinate());
				if (gameStatus.getPlayerPlay().getSector().getType() != SectorType.DANGEROUS)
					gameStatus.setSolveSectorDuty(true); // se non sei in set pericolo non devi pescare
			} 
			else {
				dtoGame.setGameMessage("Non puoi muovere in quel settore");
			}
		} 
		else {
			dtoGame.setGameMessage("Non puoi muovere adesso");
		}
		dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
		return dtoGame;
	}

}
