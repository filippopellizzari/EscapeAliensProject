package controller;

import model.*;

/**
 * 
 * @author Nicola
 *
 */

public class ItemDiscardDuty {
	
	
	private Game model;
	private Player player;
	
	/**
	 * 
	 * @param model
	 * @param player
	 */
	
	public ItemDiscardDuty(Game model, Player player) {
		this.model = model;
		this.player = player;
	}
	
	/**
	 * 
	 * @param itemCardType
	 * @return
	 */
	
	public String discard(ItemCardType itemCardType){
		for(int i = 0; i < player.getItem().size(); i++ ){
			if(player.getItem().get(i).getType().equals(itemCardType)){
				model.getItemCards().discard(player.getItem().remove(i));
				return "Hai scartato correttamente la carta "+itemCardType+"\n"; //PRIVATO
			}
			
		}
		return null;
	}


	

}
