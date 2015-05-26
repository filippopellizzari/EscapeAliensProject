package controller;

import model.*;

public class Turn {

	GameStatus gameStatus;
	
	public Turn(Game game, Player player) {
		this.gameStatus = new GameStatus(game, player);
	}
	
	public String turn(DTOTurn dtoTurn) {
		String response="";
		TryToDoAnAction actionToDo;
		switch(dtoTurn.getTypeOfAction()){
			case ATTACK : actionToDo=new Attack(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
			case USEITEM : actionToDo=new UseItem(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
			case DISCARD : actionToDo=new Discard(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
			case DRAWSECTORCARD : actionToDo=new Draw(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
			case ENDTURN : actionToDo=new EndTurn(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
			case SELECTSECTORFORNOISE: actionToDo=new SelectSectorNoise(gameStatus);
			response=actionToDo.doAction(dtoTurn);
			break;
		}
		return response;
	}

	/**
	 * @return the gameStatus
	 */
	
	public GameStatus getGameStatus() {
		return gameStatus;
	}
}