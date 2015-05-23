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
	
	
	public UseItem(Game model, Player player) {
		
		this.model = model;
		this.player = player;
		this.itemRules = new ItemRules(model, player);
	}


	public String teleport(){
		if(itemRules.teleportCheck()){
			String s = player + "sta usando una carta oggetto\n";
			Coordinate humanSector = model.getMap().getHumanSector();
			model.getMap().getSector(humanSector).addPlayer(player.getCurrentSector().removePlayer());
			return s;
		}
		return null;
		
	}
	
	public String sedatives(){
		if(itemRules.sedativesCheck()){
			String s = player + " sta usando una carta oggetto\n";
			return s;
		}
		return null;
	}
	
	
	public String spotlight(Sector chosen){
		if(itemRules.spotlightCheck()){
			String s = player + " sta usando una carta oggetto\n";
			for(int i = 0; i < chosen.getPlayers().size(); i++) {
				Player declaring = chosen.getPlayers().get(i);
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
		
	
	
	public String adrenaline(){
		if(itemRules.adrenalineCheck()){
			String s = player + " sta usando una carta oggetto\n";
			player.setSpeed(2);  //aumento la velocità dell'umano, alla fine del turno torna a 1
			return s;
		}
		return null;
		
	}
		
	public String attack(){
		if(itemRules.attackCheck()){
			String s = player + " sta usando una carta oggetto\n";
			s += new Attack(model,player).attackMove();
			return s;
		}
		return null;
	}
	
	
	
	
}












