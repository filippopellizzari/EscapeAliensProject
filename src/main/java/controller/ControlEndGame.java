package controller;

import model.Game;
import model.PlayerType;

/**
 * This class control if the game is finish and if it is true delete the game and disconnect the client
 * @author Nicola
 *
 */

public class ControlEndGame {
	
	/**
	 * 
	 * @param game, reference at model
	 * @param turnNumber, number of turn
	 */

	public boolean control(Game game, int turnNumber) {
		if(turnNumber>39) return true;
		boolean humanAlive=false;
		for(int i=0;i<game.getPlayers().length;i++) {			//controllo umani vivi
			if(game.getPlayers(i).getType()==PlayerType.HUMAN&& game.getPlayers(i).isAlive()) 
				humanAlive=true;
		}
		if(humanAlive==false) 
			return true;
		boolean hatchOpen=false;
		for(int i=0;i<game.getMap().getHatchSectors().size();i++) {		//controllo portelloni
			if(game.getMap().getSector(game.getMap().getHatchSectors().get(i)).isClosed()==false)
				hatchOpen=true;
		}
		if(hatchOpen==false) 
			return true;
		return false;
	}
	

}
