package controller;

import model.*;


public class MoveRules {
	
	private Game model;
	private Player player;
	
	
	public MoveRules(Game model, Player player) {
		this.model = model;
		this.player = player;
	}

	/**
	 * controlla che la mossa sia valida
	 * @param dest
	 * @return
	 */

	public boolean moveCheck(Coordinate dest){
		
		if(!model.getMap().isNull(dest)){
			return distanceCheck(player.getCurrentSector().getCoordinate(), dest, player.getSpeed());
		}
		return false;	
	}

	/**
	 * controlla che la destinazione sia raggiungibile secondo la velocità del giocatore e che le caselle lungo il percorso siano tutte attraversabili
	 * @param curr
	 * @param dest
	 * @param speed
	 * @return
	 */
	public boolean distanceCheck(Coordinate curr,Coordinate dest, int speed){
		
			if(speed == 0){
				return (curr.equals(dest));
			}
			else{
				Sector currSector = model.getMap().getSector(curr);
				for(int i = 0; i < currSector.getAdjacent().size(); i++){
					Coordinate adjCoord = currSector.getAdjacent().get(i);
					Sector adjSector = model.getMap().getSector(adjCoord);
					if (!model.getMap().isNull(adjCoord)){
						if(!adjSector.isClosed()){
							speed--;
							if(distanceCheck(adjCoord, dest, speed)){
								return true;					
							}
						}
					}
				}
				return false;
			}			
	}
	
	/**
	 * controlla che una casella sia attraversabile da un giocatore
	 * @return
	 */
	public boolean crossableCheck(Sector s){
		//TODO
		if (s.isClosed()){
			return false;
		}
		return true;
	}
	
	
	
	
	

	
	
	
}
