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
		//TODO
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
		 //TODO
			if(speed == 0){
				return (curr.equals(dest));
			}
			else{
				Sector s = model.getMap().getSector(curr);
				for(int i = 0; i < s.getAdjacent().size(); i++){
					if (s.getAdjacent().get(i) != null && crossableCheck(s)){
						speed = speed -1;
						if(distanceCheck(s.getAdjacent().get(i), dest, speed))
							return true;						
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
		if (player.getPlayerType() == PlayerType.ALIEN){
			return (s.getSectorType() != SectorType.HATCH);
		}
		return true;
	}
	
	
	
	
	

	
	
	
}
