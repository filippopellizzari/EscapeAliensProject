package controller;

import model.*;

/**
 * La classe contiene le regole per lo spostamento all'interno della mappa
 * @author Filippo
 *
 */
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

		return pathCheck(player.getSector().getCoordinate(), dest, player.getSpeed())
				&& destCheck (dest);		
	}

	/**
	 * controlla che la destinazione sia dentro la mappa; 
	 * nel caso degli alieni, controlla che non possano accedere ad un settore scialuppa
	 * @param dest
	 * @return
	 */
	
	private boolean destCheck(Coordinate dest){
		if(!model.getMap().isNull(dest)){
			if(player.getType()==PlayerType.ALIEN 
			   && model.getMap().getSector(dest).getType().equals(SectorType.HATCH)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * controlla che la destinazione sia raggiungibile secondo la velocità del giocatore
	 *  e che i settori lungo il percorso siano tutti attraversabili
	 * @param curr, coordinata di partenza
	 * @param dest, coordinata di destinazione
	 * @param speed, velocità del giocatore, ossia di quanti settori può spostarsi
	 * @return
	 */
	
	
	//DA CORREGGERE ASSOLUTAMENTE!
	
	public boolean pathCheck(Coordinate curr,Coordinate dest, int speed){
			if (speed==0){
					return curr.equals(dest);
			}
			
			else{
				
				Sector currSector = model.getMap().getSector(curr);
				for(int i = 0; i < currSector.getAdjacent().size(); i++){
					Coordinate adjCoord = currSector.getAdjacent().get(i);
					
					if (adjCoord.getX() != -1 && adjCoord.getY() != -1){
						Sector adjSector = model.getMap().getSector(adjCoord);
						if(!adjSector.isClosed()){
							speed--;
							if (pathCheck(adjCoord, dest, speed)){
								return true;
							}
							speed++;
						}
							
					}
				}
					
					
				}
				
			
			return false;		
	}
	



}
