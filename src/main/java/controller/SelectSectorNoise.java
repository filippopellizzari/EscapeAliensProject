package controller;

import dto.*;

/**
 * This class is used to show the effect of "noise in any sector"; the player,
 * who has drawn the dangerous sector card, must select a random sector
 *
 * 
 * @author Nicola
 *
 */

public class SelectSectorNoise implements ChooseAnAction {

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus
	 *            status of the game
	 */

	public SelectSectorNoise(GameStatus gameStatus) {
		this.status = gameStatus;
		this.dtoGame = new DTOGame();
	}

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (status.isMustNoise()) {
			status.setMustNoise(false);
			dtoGame.setReceiver(9);
			dtoGame.setCoordinate(dtoTurn.getCoordinate(), status.getPlayer()
					.getNumber());
			dtoGame.setActionType(ActionType.SELECTSECTORNOISE);// notifica noise
		} else {
			dtoGame.setGameMessage("Non puoi usare in questo momento il Noise in Any Sector");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}
		return dtoGame;
	}

}
