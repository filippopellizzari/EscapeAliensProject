package controller;
import dto.DTOTurn;
import model.*;


public class Draw implements TryToDoAnAction{
	
	private GameStatus gameStatus;
	public Draw(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}

	public String drawSectorCard(){
		String s = "";
		SectorCard current = gameStatus.getGame().getSectorCards().draw();
		SectorCardType type = current.getType();
		switch(type){
			case NOISEANY: 
				s += "NOISE IN ANY SECTOR: scegli una coordinata\n"; //messaggio privato
				return s;
		
			case NOISEYOUR: 
				s += gameStatus.getPlayerPlay() +" : NOISE IN SECTOR "+gameStatus.getPlayerPlay().getSector().getCoordinate()+"\n"; //messaggio pubblico
			    break;	
				
			case SILENCE: 
				s += gameStatus.getPlayerPlay() +" : SILENCE IN ALL SECTORS\n"; //messaggio pubblico
				break;
			default:
				break;
						
		}
		if (current.isItemIcon()){
			s+="Devi pescare una carta oggetto\n";//messaggio privato
			s+=drawItemCard();
		}
		gameStatus.getGame().getSectorCards().discard(current);
		return s;
		
		
	}
	
	private String drawItemCard(){
		String s = "";
		ItemCard current = gameStatus.getGame().getItemCards().draw();
		if(current == null){
			s+="Sono finite le carte oggetto!\n";
			return s;
		}
		gameStatus.getPlayerPlay().addItem(current);
		ItemCardType type = current.getType();
		s+="Hai pescato una carta oggetto "+type+"\n"; //messaggio privato
		if(gameStatus.getPlayerPlay().getItem().size() == 4){
			s+="Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"; //messaggio privato
		}
		return s;
	}
	
	public String drawHatchCard(){
		String s = "";
		HatchCard current = gameStatus.getGame().getHatchCards().draw();
		HatchCardColor color = current.getColor();
		switch(color){
		  	case RED :
		  		s+= gameStatus.getPlayerPlay() +" ha pescato una carta scialuppa rossa: non può scappare!\n"; //messaggio pubblico
		  		break;
		  	case GREEN :
		  		s+= gameStatus.getGame() +" ha pescato una carta scialuppa verde: ha vinto!\n"; //messaggio pubblico
		  		s+= "HAI VINTO!\n"; //messaggio privato
		  		break;
		}
		gameStatus.getGame().getHatchCards().discard(current);
		return s;	
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()&&gameStatus.isAttack()==false&&gameStatus.isNoiseInAnySector()&&gameStatus.isSolveSectorDuty()==false){   //pesca carta settore pericoloso
			gameStatus.setSolveSectorDuty(true);
			String response=drawSectorCard();
			if(response.substring(0,11)=="NOISE IN ANY") 
				gameStatus.setNoiseInAnySector(true);	//controlla se è un noise
			if(gameStatus.getPlayerPlay().getItem().size()==4) 
				gameStatus.setDiscardItemDuty(true);
			return response;
		}
		else return "Non puoi pescare in questo momento";
	}

}
