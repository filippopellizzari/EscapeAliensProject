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
		 	if(player.getPlayerType()==PlayerType.ALIEN && model.getMap().getSector(dest).getSectorType()==SectorType.HATCH) return false;
			if(speed == 0){
				return (curr.equals(dest));
			}
			else{
				Sector s = model.getMap().getSector(curr);
				Coordinate coordinateNull=new Coordinate(-1,-1);
				for(int i = 0; i < s.getAdjacent().size(); i++){
					if (model.getMap().isNull(s.getAdjacent().get(i))==false)
						if(crossableCheck(model.getMap().getSector(s.getAdjacent().get(i)))) {
							speed--;
							if(distanceCheck(s.getAdjacent().get(i), dest, speed))
								return true;	
						}
				}
			}
			return false;
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
