package controller;
import model.*;

/**
 * 
 * @author Filippo
 *
 */
public class CardsEffect {
	
	
	private Game model;
	private Player player;
	
	/**
	 * 
	 * @param model, reference at model
	 * @param player, reference at player that has to play
	 */
	
	public CardsEffect(Game model, Player player) {
		this.model = model;
		this.player = player;
	}
	
	
	public String drawSectorCard(){
		String s = "";
		SectorCard current = model.getSectorCards().draw();
		switch(current.getType()){
			case NOISEANY: 
				s += "NOISE IN ANY SECTOR: scegli una coordinata!\n"; //PRIVATO
				break;
		
			case NOISEYOUR: 
				s += player +" : NOISE IN SECTOR "+player.getSector().getCoordinate()+"\n"; //PUBBLICO
				break;
				
			case SILENCE: 
				s += player +" : SILENCE IN ALL SECTORS\n"; //PUBBLICO
				break;		
		}
		if (current.isItemIcon()){
			s+="ITEM ICON!\n"; //PRIVATO
			s+=drawItemCard();
		}
		model.getSectorCards().discard(current);
		return s;
		
		
	}

	private String drawItemCard(){
		String s = "";
		ItemCard current = model.getItemCards().draw();
		if(current == null){
			s+="Sono finite le carte oggetto!\n"; //PRIVATO
			return s;
		}
		player.addItem(current);
		ItemCardType type = current.getType();
		s+="Hai pescato una carta oggetto "+type+"\n"; //PRIVATO
		if(player.getItem().size() == 4){
			s+="Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"; //PRIVATO
		}
		return s;
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
