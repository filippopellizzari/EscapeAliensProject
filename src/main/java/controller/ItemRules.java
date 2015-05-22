package controller;

import model.*;

public class ItemRules {
	
	
	private Player player;
	
	

	public ItemRules(Player player) {
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
		return (player.getPlayerType() == PlayerType.HUMAN);
	}
	
	private boolean cardCheck(ItemCardType type){
		for(int i = 0; i < player.getItemCardPlayer().size(); i++){
			if(player.getItemCardPlayer().get(i).getItemCardType() == type){
				return true;
			}
		}
		
		return false;
			
	}
	
	


}
