package controller;

import java.util.Random;

import model.*;

public class CompleteTurn {
	private GameStatus gameStatus;

	public CompleteTurn(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}

	public String completeTurn() {
		TryToDoAnAction actionToDo;
		Random random = new Random();					//random per le azioni che devono essere fatte
		int condizione;					//se arriva a 4 vuol dire che il turno è finito
		String response="";
		do{
			condizione=0;
			if(gameStatus.isMove()==false){ //non ha mosso
				actionToDo=new MoveRules(gameStatus);
				response=actionToDo.doAction(new DTOTurn(null,null,null));
			}
			else condizione++;
			if(gameStatus.isDiscardItemDuty()) { //non ha scartato
				actionToDo=new Discard(gameStatus);
				response=actionToDo.doAction(new DTOTurn(null,gameStatus.getPlayerPlay().getItem().get(random.nextInt(4)).getType(),null));
			}
			else condizione++;
			if(gameStatus.isSolveSectorDuty()==false) {		
				if(gameStatus.getPlayerPlay().getSector().getType()==SectorType.DANGEROUS) { //verifica che debba pescare la carta settore pericoloso
					actionToDo=new Draw(gameStatus);
					response=actionToDo.doAction(new DTOTurn(null,null,null));
				}
				else condizione++;
			}
			else condizione++;
			if(gameStatus.isNoiseInAnySector()) { //non ha usato il rumore
				Coordinate coordinateRandom;
				do{
					 coordinateRandom=new Coordinate(random.nextInt(22)+1, random.nextInt(13)+1);		//sorteggio coordinata a caso
				}while(gameStatus.getGame().getMap().isNull(coordinateRandom)==false);
				actionToDo=new SelectSectorNoise(gameStatus);
				Coordinate coordinate;		//coordinata a caso
				response=actionToDo.doAction(new DTOTurn(coordinateRandom,null,null));
				//usa il rumore a caso
			}
			else condizione++;
		} while(condizione<=3);
		return response;
	}
}
