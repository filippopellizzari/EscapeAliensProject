package controller;
import model.*;

public class MoveAction {

	
	private Game model;
	private Player player;

	
	public MoveAction(Game model, Player player){
		this.model = model;
		this.player = player;
	}
	
	public String move(Coordinate destCoord){
			Sector destSector = model.getMap().getSector(destCoord);
			destSector.addPlayer(player.getSector().removePlayer());
			player.setSector(destSector);
			String s = "Ti sei spostato nel settore "+destCoord+"\n"; //PRIVATO
			switch(destSector.getType()){
				case DANGEROUS :
					s += "Sei finito su un settore pericoloso!\n"; //PRIVATO
					s += "Puoi decidere se pescare una carta settore pericoloso, attaccare o giocare carta oggetto\n"; //PRIVATO
				break;
				case HATCH : 
					s += "Sei finito su un settore hatch!\n"; //PRIVATO
					s += player + " si trova nel settore "+destSector+"\n"; //PUBBLICO
					s+= new CardsEffect(model, player).drawHatchCard();
				break;
				case SECURE :
					s += "Sei finito su un settore sicuro!\n";
				break;
			default:
				break;
			}
			return s;
			
	}
	
	
	
	
	
}
