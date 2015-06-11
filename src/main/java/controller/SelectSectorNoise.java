package controller;

import dto.*;

/**
 * This class is used to show at every player the coordinate of the move in any sector obviously they don't know that the
 * card is noise in any sector except for the player that has draw the card
 * @author Nicola
 *
 */

public class SelectSectorNoise implements ChooseAnAction {

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
		if (gameStatus.isMoved() && gameStatus.isMustNoise()){ 
			gameStatus.setMustNoise(false);	
			dtoGame.setReceiver(9);			
			dtoGame.setCoordinate(dtoTurn.getCoordinate(), gameStatus.getPlayer().getNumber());	//notifica noise
		} else {
			dtoGame.setGameMessage("Non puoi usare in questo momento il Noise in Any Sector");
			dtoGame.setReceiver(gameStatus.getPlayer().getNumber());	//notifica privata
		}
		return dtoGame;
	}

}
