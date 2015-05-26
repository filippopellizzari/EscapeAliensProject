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
		  	case GREEN :
		  		s+= player +" ha pescato una carta scialuppa verde: ha vinto!\n"; //PUBBLICO
		  		s+= "HAI VINTO!\n"; //PRIVATO
		}
		model.getHatchCards().discard(current);
		return s;	
	}
	
	public String discardItemDuty(ItemCardType itemCardType){
		for(int i = 0; i < player.getItem().size(); i++ ){
			if(player.getItem().get(i).getType().equals(itemCardType)){
				model.getItemCards().discard(player.getItem().remove(i));
				return "Hai scartato correttamente la carta "+itemCardType+"\n"; //PRIVATO
			}
			
		}
		return null;
	}
	
	
	
	
	
	
	
	

}
