package controller;

import model.*;


public class DrawItemCard {
	
	private Game model;
	private Player player;
	
	/**
	 * 
	 * @param model, reference at model
	 * @param player, reference at player that has to play
	 */
	
	public DrawItemCard(Game model, Player player) {
		this.model = model;
		this.player = player;
	}
	
	public String drawItemCard(){
		String s = "";
		ItemCard current = model.getItemCards().draw();
		if(current == null){
			s+="Sono finite le carte oggetto!\n";
			return s;
		}
		player.addItem(current);
		ItemCardType type = current.getType();
		s+="Hai pescato una carta oggetto "+type+"\n"; //messaggio privato
		if(player.getItem().size() == 4){
			s+="Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"; //messaggio privato
		}
		return s;
	}

}
