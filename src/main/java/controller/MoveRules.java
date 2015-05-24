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
			if(player.getType()==PlayerType.ALIEN 
			   && model.getMap().getSector(dest).getType()==SectorType.HATCH) {
				return false;
			}
			return pathCheck(player.getSector().getCoordinate(), dest, player.getSpeed());
		}
		return false;	
	}

	/**
	 * controlla che la destinazione sia raggiungibile secondo la velocità del giocatore
	 *  e che le caselle lungo il percorso siano tutte attraversabili
	 * @param curr
	 * @param dest
	 * @param speed
	 * @return
	 */
	
	
	//DA CORREGGERE ASSOLUTAMENTE!
	
	public boolean pathCheck(Coordinate curr,Coordinate dest, int count){
			if (curr.getX() == dest.getX() && curr.getY() == dest.getY()){
					return true;
			}
			
			else{
				count++;
				Sector currSector = model.getMap().getSector(curr);
				for(int i = 0; i < currSector.getAdjacent().size(); i++){
					Coordinate adjCoord = currSector.getAdjacent().get(i);
					
					if (adjCoord.getX() != -1 && adjCoord.getY() != -1){
						Sector adjSector = model.getMap().getSector(adjCoord);
						if(!adjSector.isClosed()){
							if (pathCheck(adjCoord, dest, count)){
								return true;
							}						
						}
							
					}
				}
					
					
				}
				
			
			return false;
					
			
	}
	



}
