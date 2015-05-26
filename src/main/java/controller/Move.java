package controller;

import model.*;

/**
 * This class contains the control of the move and the effective action of move
 * 
 * @author Filippo
 *
 */
public class Move implements TryToDoAnAction {

	private GameStatus gameStatus;

	public Move(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
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

	public String move(Coordinate destCoord) {
		Sector destSector = gameStatus.getGame().getMap().getSector(destCoord);
		destSector.addPlayer(gameStatus.getPlayerPlay().getSector()
				.removePlayer());
		gameStatus.getPlayerPlay().setSector(destSector);
		String s = "PR Ti sei spostato nel settore " + destCoord + "\n";
		switch (destSector.getType()) {
		case DANGEROUS:
			s += "PR Sei finito su un settore pericoloso!\n";
			s += "PR Puoi decidere se pescare una carta settore pericoloso, attaccare o giocare carta oggetto\n";
			break;
		case HATCH:
			s += "PR Sei finito su un settore hatch!\n";
			s += gameStatus.getPlayerPlay() + " si trova nel settore "
					+ destSector + "\n";
			s += new CardsEffect(gameStatus.getGame(),
					gameStatus.getPlayerPlay()).drawHatchCard();
			break;
		case SECURE:
			s += "PR Sei finito su un settore sicuro!\n";
			break;
		default:
			break;
		}
		return s;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() == false && dtoTurn.getTypeCard() == null
				&& dtoTurn.getCoordinate() != null) { // mossa
			if (moveCheck(dtoTurn.getCoordinate())) {
				gameStatus.setMove(true);
				String response = move(dtoTurn.getCoordinate());
				if (gameStatus.getPlayerPlay().getSector().getType() != SectorType.DANGEROUS)
					gameStatus.setSolveSectorDuty(true); // se non sei in set
															// pericolo non devi
															// pescare
				return response;
			} else
				return "Non puoi muovere in quel settore";
		} else
			return "Non puoi muovere adesso";
	}

}
