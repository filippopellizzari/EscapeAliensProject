package controller;
import model.*;

/**
 * 
 * @author Filippo
 *
 */
public class DrawHatchCard {
	
	
	private Game model;
	private Player player;
	
	/**
	 * 
	 * @param model, reference at model
	 * @param player, reference at player that has to play
	 */
	
	public DrawHatchCard(Game model, Player player) {
		this.model = model;
		this.player = player;
	}
	
	
	public String drawHatchCard(){
		String s = "";
		HatchCard current = model.getHatchCards().draw();
		HatchCardColor color = current.getColor();
		switch(color){
		  	case RED :
		  		s+= player +" ha pescato una carta scialuppa rossa: non può scappare!\n"; //PUBBLICO
		  		break;
		  	case GREEN :
		  		s+= player +" ha pescato una carta scialuppa verde: ha vinto!\n"; //PUBBLICO
		  		s+= "HAI VINTO!\n"; //PRIVATO
		  		break;
		}
		model.getHatchCards().discard(current);
		return s;	
	}
	
	
	
	
	
	
	
	
	

}
