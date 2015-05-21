package controller;

import model.*;

/**
 * 
 * @author filippopellizzari
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
	public void attackMove(){
		Sector current = player.getCurrentSector();
		System.out.println(player+" : ATTACK in "+current); //il giocatore dichiara l'attacco
		for(int i = 0; i < current.getPlayers().size()-1; i++){
			Player attacked = current.removePlayer();	//tolgo giocatore eliminato dalla mappa 
			System.out.println(attacked+" : è attaccato!"); //se un giocatore si trova nel settore, dichiara la propria presenza
			if(isDefendable(attacked)){
				System.out.println(attacked+" : si salva grazie alla carta Difesa");
				current.addPlayer(attacked); 	//rimetto il giocatore nel settore
			}
			else{
				System.out.println(attacked+" è ucciso e viene eliminato dal gioco: la sua identità era "+
							attacked.getPlayerType());
				attacked.setAlive(false); //? ha più senso toglierlo dal modello? 
				for(int j = 0; j < attacked.getItemCardPlayer().size(); j++){ //scarto tutte le carte oggetto del giocatore eliminato
					model.getItemCards().discard(attacked.getItemCardPlayer().get(j));
				}	
			}
		}
		current.addPlayer(current.removePlayer()); 	//rimetto l'attaccante in coda così se c'era uno che aveva la difesa mi basta fare remove per muoverlo
	}
	
	
	/**
	 * 
	 * @param attacked, il giocatore attaccato
	 * @return true, se il giocatore attaccato possiede la carta Difesa
	 */
			
	private boolean isDefendable(Player attacked){
		for(int j = 0; j < attacked.getItemCardPlayer().size(); j++){
			if(attacked.getItemCardPlayer().get(j).getItemCardType() == ItemCardType.DEFENSE){
				return true;
			}
		}
		return false;	
	}
	
	

			
			
			
}
