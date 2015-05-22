package controller;
import model.*;

/**
 * azioni sulle carte oggetto; possono essere eseguite solo da giocatori di tipo "umano"
 * 
 * @author Filippo
 *
 */
public class UseItem {
	
	
	private Game model;
	private Player player;
	private ItemRules itemRules;
	
	
	public UseItem(Game model, Player player, ItemRules itemRules) {
		
		this.model = model;
		this.player = player;
		this.itemRules = itemRules;
	}


	public void teleport(){
		if(itemRules.teleportCheck()){
			Coordinate humanSector = model.getMap().getHumanSector();
			model.getMap().getSector(humanSector).addPlayer(player.getCurrentSector().removePlayer());
		}
		
	}
	
	public void sedatives(){
		if(itemRules.sedativesCheck()){
			//il giocatore diventa sedato
		}
	}
	
	
	public String spotlight(ItemCard spotlightCard, Sector chosen){
		if(itemRules.spotlightCheck()){
			String s="";
			for(int i = 0; i < chosen.getPlayers().size(); i++) {
				Player declaring=chosen.getPlayers().get(i);
				s += declaring+" in sector "+chosen.getCoordinate()+"\n";

			}
			
			for (int i = 0; i < 6; i++){
				Sector lighted = model.getMap().getSector(chosen.getAdjacent().get(i));
				if(lighted!=null)
					for(int j = 0; j < lighted.getPlayers().size(); j++){
						Player declaring = lighted.getPlayers().get(j); 
						s += declaring+" in sector "+lighted.getCoordinate()+"\n";
					}		
			}
		
		
		//ogni giocatore nel settore scelto e in quelli adiacenti devono dichiarare la loro posizione
			return s;
		}
		return null;
		
	}
		
	
	
	public void adrenaline(){
		if(itemRules.adrenalineCheck()){
			//il giocatore ha l'adrenalina
		}
		
	}
		
	public void attack(){
		if(itemRules.attackCheck()){
			new Attack(model,player).attackMove();
			//il giocatore attacca
		}
	}
	
	
	
	
}












