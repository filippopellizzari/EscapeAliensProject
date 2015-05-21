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
		
		model.getItemCards().discard(teleportCard); //scarto la carta teletrasporto (coperta)
		player.getCurrentSector().removePlayer(); //tolgo il giocatore dalla casella attuale
		Sector destination = model.getMap().getSector(model.getMap().getHumanSector());//seleziono il settore umano
		destination.addPlayer(player); //posiziono il giocatore nel settore umano
		player.setCurrentSector(destination); 
		
			
	}
	
	
	public void sedatives(Game model, ItemCard sedativesCard){
		
		model.getItemCards().discard(sedativesCard); //scarto la carta sedativi (coperta)
	}
	
	
	
	public void spotlight(Game model, ItemCard spotlightCard, Sector chosen){

		for(int i = 0; i < chosen.getPlayers().size(); i++){
			Player declaring = chosen.getPlayers().get(i);
			System.out.println("Sono il giocatore "+declaring+" e mi trovo nel settore "+chosen); 
		}
		
		for (int i = 0; i < 6; i++){
			Sector lighted = model.getMap().getSector(chosen.getAdjacent().get(i));
			for(int j = 0; j < lighted.getPlayers().size(); j++){
				Player declaring = lighted.getPlayers().get(j);
				System.out.println("Sono il giocatore "+declaring+" e mi trovo nel settore "+lighted); 
			}		
		}
		//ogni giocatore nel settore scelto e in quelli adiacenti devono dichiarare la loro posizione
	}
		
	
	public void adrenaline(Game model, ItemCard adrenalineCard, Player player){
		
		model.getItemCards().discard(adrenalineCard); //scarto la carta adrenalina (coperta)
	}
		
	public void attack(Game model, ItemCard attackCard, Player player){
		
		model.getItemCards().discard(attackCard); //scarto la carta attacco (coperta)
		Attack a = new Attack(model, player);
		a.attackMove();	
	}
		
	
	
	
	
	
}












