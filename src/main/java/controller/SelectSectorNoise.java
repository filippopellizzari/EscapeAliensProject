package controller;

import model.SectorCardType;
import dto.*;

/**
 * 
 * @author Nicola
 *
 */

public class SelectSectorNoise implements TryToDoAnAction {

	private GameStatus gameStatus;
	private DTOGame dtoGame;
	
	/**
	 * 
	 * @param gameStatus, status of the game
	 */

	public SelectSectorNoise(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
		this.dtoGame=new DTOGame();
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && gameStatus.isNoiseInAnySector()
				&& dtoTurn.getCoordinate() != null
				&& dtoTurn.getTypeCard() == null) { // indica il settore del
													// noise in any sector
			gameStatus.setNoiseInAnySector(true);
			dtoGame.setSectorType(SectorCardType.NOISEANY);
			dtoGame.setDestination(9);
			dtoGame.setCoordinate(dtoTurn.getCoordinate(), gameStatus.getPlayerPlay().getNumber());
		} else {
			dtoGame.setGameMessage("Non puoi usare in questo momento il Noise in Any Sector");
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
		}
		return dtoGame;
	}

}
