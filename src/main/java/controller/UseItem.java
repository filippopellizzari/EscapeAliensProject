package controller;
import model.*;

/**
 * azioni sulle carte oggetto; possono essere eseguite solo da giocatori di tipo "umano"
 * 
 * @author filippopellizzari
 *
 */
public class UseItem {

	public void teleport(Game model, ItemCard teleportCard, Player player){
		
		Sector destination = model.getMap().getSector(model.getMap().getHumanSector());
		destination.addPlayer(player);
		player.setCurrentSector(destination);
		model.getItemCards().discard(teleportCard);
			
	}
	
	public void sedatives(Game model, ItemCard sedativesCard){
		
		model.getItemCards().discard(sedativesCard);			
	}
	
	public void spotlight(Game model, ItemCard spotlightCard, Coordinate chosen){

		Sector spot = model.getMap().getSector(chosen);
		for (int i=0; i<6; i++){
			System.out.println(model.getMap().getSector(spot.getAdjacent().get(i)).getPlayers());
			
		}
		
	
	}
	
	
	
}












