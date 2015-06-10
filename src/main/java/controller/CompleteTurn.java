package controller;

import java.util.List;
import java.util.Random;

import dto.*;
import model.*;

/**
 * This class complete a turn after the time of a player ends and he has not
 * finished the turn
 * 
 * @author Nicola
 *
 */

public class CompleteTurn {
	private GameStatus gameStatus;
	/**
	 * 
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */

	public CompleteTurn(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	/**
	 * 
	 * @param dtoGameList 
	 * @return the action happen during the actions, this means that can be returned more than 1 action
	 */

	public List<DTOGame> completeTurn(List<DTOGame> dtoGameList) {
		TryToDoAnAction actionToDo;
		Random random = new Random();
		DTOGame dtoGame;
		int condizione; // se arriva a 4 vuol dire che il turno è finito
		do {
			condizione = 0;
			if (!gameStatus.isMove()) { // non ha mosso
				actionToDo = new Move(gameStatus);
				do {
					dtoGame=new DTOGame();
					dtoGame = actionToDo.doAction(new DTOTurn(gameStatus
							.getPlayerPlay().getSector().getAdjacent()
							.get(random.nextInt(6)), null, TypeOfAction.MOVE));
				} while (dtoGame.getGameMessage() != "OK");
				gameStatus.setMove(true);
				dtoGameList.add(dtoGame);
			} else
				condizione++;
			if (gameStatus.isDiscardItemDuty()) { // non ha scartato
				dtoGame=new DTOGame();
				actionToDo = new Discard(gameStatus);
				dtoGame = actionToDo.doAction(new DTOTurn(null, gameStatus
				.getPlayerPlay().getItem().get(random.nextInt(4)).getType(), TypeOfAction.DISCARD));
				gameStatus.setDiscardItemDuty(true);
				dtoGameList.add(dtoGame);
			} else
				condizione++;
			if (gameStatus.isSolveSectorDuty() == false) {
				dtoGame=new DTOGame();
				if (gameStatus.getPlayerPlay().getSector().getType() == SectorType.DANGEROUS) 
				{ // verifica che debba pescare la carta settore pericoloso
					actionToDo = new DrawSectorCard(gameStatus);
					dtoGame = actionToDo.doAction(new DTOTurn(null, null, TypeOfAction.DRAWSECTORCARD));
					gameStatus.setSolveSectorDuty(true);
					dtoGameList.add(dtoGame);
				} else
					condizione++;
			} else
				condizione++;
			if (gameStatus.isNoiseInAnySector()) { // non ha usato il rumore
				Coordinate coordinateRandom;
				dtoGame=new DTOGame();
				do {
					coordinateRandom = new Coordinate(random.nextInt(22) + 1,
					random.nextInt(13) + 1); // sorteggio coordinata a caso
				} while (gameStatus.getGame().getMap().isNull(coordinateRandom) == false);
				actionToDo = new SelectSectorNoise(gameStatus);
				dtoGame = actionToDo.doAction(new DTOTurn(coordinateRandom,null, TypeOfAction.SELECTSECTORFORNOISE));	// usa il rumore a casodto
				dtoGameList.add(dtoGame);
			} else
				condizione++;
		} while (condizione <= 3);
		return dtoGameList;
	}
}
