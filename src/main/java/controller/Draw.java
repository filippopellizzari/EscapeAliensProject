package controller;
import model.*;


public class Draw {
	
	
	private Game model;
	private Player player;
	
	public Draw(Game model, Player player) {
		this.model = model;
		this.player = player;
	}
	
	
	public String drawSectorCard(){
		String s = "";
		SectorCard current = model.getSectorCards().draw();
		SectorCardType type = current.getType();
		switch(type){
			case NOISEANY: 
				s += "NOISE IN ANY SECTOR: scegli una coordinata\n"; //messaggio privato
				return s;
		
			case NOISEYOUR: 
				s += player +" : NOISE IN SECTOR "+player.getSector().getCoordinate()+"\n"; //messaggio pubblico
				
				
			case SILENCE: 
				s += player +" : SILENCE IN ALL SECTORS\n"; //messaggio pubblico
						
		}
		if (current.isItemIcon()){
			s+="Devi pescare una carta oggetto\n";//messaggio privato
			s+=drawItemCard();
		}
		model.getSectorCards().discard(current);
		return s;
		
		
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
	
	public String drawHatchCard(){
		String s = "";
		HatchCard current = model.getHatchCards().draw();
		HatchCardColor color = current.getColor();
		switch(color){
		  	case RED :
		  		s+= player +" ha pescato una carta scialuppa rossa: non può scappare!\n"; //messaggio pubblico
		  	case GREEN :
		  		s+= player +" ha pescato una carta scialuppa verde: ha vinto!\n"; //messaggio pubblico
		  		s+= "HAI VINTO!\n"; //messaggio privato
		}
		model.getHatchCards().discard(current);
		return s;	
	}
	
	
	
	
	
	
	
	
	
	

}
