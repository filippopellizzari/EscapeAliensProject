package controller;

import model.SectorType;

public class CompleteTurn {
	public String completeTurn() {
		int condizione;					//se arriva a 4 vuol dire che il turno è finito
		String s="";
		do{
			condizione=0;
			/*if(move==false){ //non ha mosso
				//muovi a caso
			}
			else condizione++;
			if(discardItemDuty==true) { //non ha scartato
				//scarta
			}
			else condizione++;
			if(solveSectorDuty==false) {		
				if(playerPlay.getSector().getType()==SectorType.DANGEROUS) { //verifica che debba pescare la carta settore pericoloso
					
				}
				else condizione++;
			}
			else condizione++;
			if(noiseInAnySector==true) { //non ha usato il rumore
				//usa il rumore a caso
			}
			else condizione++;*/
		} while(condizione<=3);
		return s;
	}
}
