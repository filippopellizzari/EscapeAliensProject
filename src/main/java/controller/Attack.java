package controller;

import model.*;

/**
 * 
 * @author Filippo
 *
 */

public class Attack {
	
	private Game model;
	private Player player;
	
	public Attack(Game model, Player player) {
		this.model = model;
		this.player = player;
	}

	
	
	/**
	 * mossa di attacco (vale per tutti i tipi di giocatore)
	 */
	public String attackMove(){
		String s = "";
		Sector current = player.getCurrentSector();
		s += player+" : ATTACK in "+current+"\n"; //il giocatore dichiara l'attacco in un settore
		for(int i = 0; i < current.getPlayers().size()-1; i++){
			Player attacked = current.removePlayer();	//prendo il giocatore sotto attacco in quel settore
			s += attacked+" è sotto attacco!\n"; //se un giocatore si trova nel settore, dichiara la propria presenza
			if(isDefendable(attacked)){
				s += attacked+" : si salva grazie alla carta Difesa!\n";
				current.addPlayer(attacked); 	//rimetto il giocatore nel settore
			}
			else{
				s += attacked+" è ucciso e viene eliminato dal gioco: la sua identità era "+
							attacked.getPlayerType()+"\n";
				attacked.setAlive(false); //? ha più senso toglierlo dal modello? 
				for(int j = 0; j < attacked.getItemCardPlayer().size(); j++){ //scarto tutte le carte oggetto del giocatore eliminato
					model.getItemCards().discard(attacked.getItemCardPlayer().get(j));
				}	
			}
		}
		current.addPlayer(current.removePlayer()); 	//rimetto l'attaccante in coda 
		return s;
	}
	
	
	/**
	 * 
	 * @param attacked, il giocatore attaccato
	 * @return true, se il giocatore attaccato possiede la carta Difesa
	 */
			
	private boolean isDefendable(Player attacked){
		for(int j = 0; j < attacked.getItemCardPlayer().size(); j++){
			if(attacked.getItemCardPlayer().get(j).getItemCardType() == ItemCardType.DEFENSE){
				model.getItemCards().discard(attacked.removeItemCardPlayer(j)); //scarto carta difesa  
				return true;
			}
		}
		return false;	
	}
	
	

			
			
			
}
