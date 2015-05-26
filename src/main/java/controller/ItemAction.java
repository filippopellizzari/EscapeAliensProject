package controller;

import model.*;

/**
 * azioni sulle carte oggetto; possono essere eseguite solo da giocatori di tipo "umano"
 * @author Filippo
 *
 */
public class ItemAction {
	
	
	private Game model;
	private Player player;
	
	public ItemAction(Game model, Player player) {
		this.model = model;
		this.player = player;
	}


	public String teleport(){
		    discard(ItemCardType.TELEPORT);
			String s = player + "sta usando una carta oggetto\n";
			Coordinate humanSector = model.getMap().getHumanCoord();
			model.getMap().getSector(humanSector).addPlayer(player.getSector().removePlayer());
			return s;		
	}
	
	public String sedatives(){
		    discard(ItemCardType.SEDATIVES);
			String s = player + " sta usando una carta oggetto\n";
			player.setSedated(true);
			return s;
	}
	
	public String spotlight(Coordinate chosenCoord){
			discard(ItemCardType.SPOTLIGHT);
			String s = player + " sta usando una carta oggetto\n";
			Sector chosenSector = model.getMap().getSector(chosenCoord);
			for(int i = 0; i < chosenSector.getPlayers().size(); i++) {
				Player declaring = chosenSector.getPlayers().get(i);
				s += declaring+" in sector "+chosenSector.getCoordinate()+"\n";

			}
			
			for (int i = 0; i < 6; i++){
				Sector lighted = model.getMap().getSector(chosenSector.getAdjacent().get(i));
				if(lighted!=null)
					for(int j = 0; j < lighted.getPlayers().size(); j++){
						Player declaring = lighted.getPlayers().get(j); 
						s += declaring+" in sector "+lighted.getCoordinate()+"\n";
					}		
			}
			return s;
	}
		
	public String adrenaline(){
			discard(ItemCardType.ADRENALINE);
			String s = player + " sta usando una carta oggetto\n";
			player.setSpeed(2);  
			return s;
	}
		
	public String attack(){
			discard(ItemCardType.ATTACK);
			String s = player + " sta usando una carta oggetto\n";
			s += new Attack(model,player).attackMove();
			return s;
	}
	
	
	
	private void discard(ItemCardType type){
		for(int i = 0; i < player.getItem().size(); i++){
			if(player.getItem().get(i).getType().equals(type)){
				model.getItemCards().discard(player.removeItem(i));
			}
		}
	}
	
	

}
