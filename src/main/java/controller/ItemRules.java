package controller;

import model.*;

public class ItemRules {
	
	private Game model;
	private Player player;
	
	

	public ItemRules(Game model, Player player) {
		this.model = model;
		this.player = player;
	}

	public boolean teleportCheck(){
		return humanCheck() && cardCheck(ItemCardType.TELEPORT);
	}
	
	public boolean sedativesCheck(){
		return humanCheck() && cardCheck(ItemCardType.SEDATIVES);
	}
	
	public boolean spotlightCheck(){
		return humanCheck() && cardCheck(ItemCardType.SPOTLIGHT);
	}
	
	public boolean adrenalineCheck(){
		return humanCheck() && cardCheck(ItemCardType.ADRENALINE);
	}
	public boolean attackCheck(){
		return humanCheck() && cardCheck(ItemCardType.ATTACK);
	}
	
	
	
	private boolean humanCheck(){
		return player.getPlayerType() == PlayerType.HUMAN;
	}
	
	private boolean cardCheck(ItemCardType type){
		for(int i = 0; i < player.getItemCardPlayer().size(); i++){
			if(player.getItemCardPlayer().get(i).getItemCardType() == type){
				model.getItemCards().discard(player.removeItemCardPlayer(i)); //se ha la carta oggetto la scarta (coperta) e può fare l'azione
				return true;
			}
		}
		
		return false;
			
	}
	
	


}
