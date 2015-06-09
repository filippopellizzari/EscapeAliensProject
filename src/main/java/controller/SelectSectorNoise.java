package controller;

import dto.*;

/**
 * This class is used to show at every player the coordinate of the move in any sector obviously they don't know that the
 * card is noise in any sector except for the player that has draw the card
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
				&& dtoTurn.getTypeCard() == null) { // indica il settore del noise in any sector
			gameStatus.setNoiseInAnySector(true);	//hai usato il noise
			dtoGame.setDestination(9);				//non passo la carta poichè i giocatori non devono saperla
			dtoGame.setCoordinate(dtoTurn.getCoordinate(), gameStatus.getPlayerPlay().getNumber());		//settore del noise nella casella del giocatore
		} else {
			dtoGame.setGameMessage("Non puoi usare in questo momento il Noise in Any Sector");
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());		//solo lui deve ricevere il messaggio
		}
		return dtoGame;
	}

}
