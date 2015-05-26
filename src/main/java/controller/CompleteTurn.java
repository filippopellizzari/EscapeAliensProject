package controller;

import model.SectorType;

public class CompleteTurn {
	private GameStatus gameStatus;

	public CompleteTurn(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}

	public String completeTurn() {
		int condizione;					//se arriva a 4 vuol dire che il turno è finito
		String s="";
		do{
			condizione=0;
			if(gameStatus.isMove()==false){ //non ha mosso
				//muovi a caso
			}
			else condizione++;
			if(gameStatus.isDiscardItemDuty()) { //non ha scartato
				//scarta
			}
			else condizione++;
			if(gameStatus.isSolveSectorDuty()==false) {		
				if(gameStatus.getPlayerPlay().getSector().getType()==SectorType.DANGEROUS) { //verifica che debba pescare la carta settore pericoloso
					
				}
				else condizione++;
			}
			else condizione++;
			if(gameStatus.isNoiseInAnySector()) { //non ha usato il rumore
				//usa il rumore a caso
			}
			else condizione++;
		} while(condizione<=3);
		return s;
	}
}
