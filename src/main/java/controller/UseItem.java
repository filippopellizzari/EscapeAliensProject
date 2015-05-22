package controller;
import model.*;

/**
 * azioni sulle carte oggetto; possono essere eseguite solo da giocatori di tipo "umano"
 * 
 * @author Filippo
 *
 */
public class UseItem {

	public void teleport(Game model, ItemCard teleportCard, Player player){
		
		model.getItemCards().discard(teleportCard); //scarto la carta teletrasporto (coperta)
		model.getMap().getSector(model.getMap().getHumanSector()).addPlayer(player.getCurrentSector().removePlayer());//seleziono il settore umano
	}
	
	public void sedatives(Game model, ItemCard sedativesCard){
		
		model.getItemCards().discard(sedativesCard); //scarto la carta sedativi (coperta)
		//manca la modifica alla variabile di stato del controller
	}
	
	public String spotlight(Game model, ItemCard spotlightCard, Sector chosen){
		String s="";
		for(int i = 0; i < chosen.getPlayers().size(); i++) {
			Player declaring=chosen.getPlayers().get(i);
			s+="Player "+declaring.getNumberOfPlayer()+" tipo: "+declaring.getPlayerType()+" in sector "+chosen.getCoordinate().getX()+" "+chosen.getCoordinate().getY()+"\n";
		}
			for (int i = 0; i < 6; i++){
			Sector lighted = model.getMap().getSector(chosen.getAdjacent().get(i));
			if(lighted!=null)
				for(int j = 0; j < lighted.getPlayers().size(); j++){
					Player declaring = lighted.getPlayers().get(j);
					System.out.println("Sono il giocatore "+declaring+" e mi trovo nel settore "+lighted); 
					s+="Player "+declaring.getNumberOfPlayer()+" tipo: "+declaring.getPlayerType()+" in sector "+lighted.getCoordinate().getX()+" "+lighted.getCoordinate().getY()+"\n";
				}		
		}
		//ogni giocatore nel settore scelto e in quelli adiacenti devono dichiarare la loro posizione
		return s;
	}
		
	
	public void adrenaline(Game model, ItemCard adrenalineCard){
		
		model.getItemCards().discard(adrenalineCard); //scarto la carta adrenalina (coperta)
		
	}
		
	public void attack(Game model, ItemCard attackCard, Player player){
		
		model.getItemCards().discard(attackCard); //scarto la carta attacco (coperta)
		Attack a = new Attack(model, player);
		a.attackMove();	
	}
	
}












