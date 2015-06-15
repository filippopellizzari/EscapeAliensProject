package controller;

import model.Game;
import model.PlayerType;
import dto.*;

/**
 * This class control if the game is finished; 
 * if it's true, deletes the game and disconnects all players
 * @author Nicola
 *
 */

public class EndGame {
	
	private final Game game;
	private final int round;
	private final int TOT_ROUNDS = 39;

	public EndGame(Game game, int round){
		this.game = game;
		this.round = round;	
	}
	
	public boolean control() {
		return (round > TOT_ROUNDS) || !humanAlive() || !hatchOpen();
	}
	
	private boolean humanAlive(){//controllo umani vivi
		for(int i=0;i<game.getPlayers().length;i++) {			
			if(game.getPlayers(i).getType().equals(PlayerType.HUMAN)
					&& game.getPlayers(i).isAlive()) 
				return true;
		}
		return false;
	}
	
	private boolean hatchOpen(){//controllo portelloni
		for(int i=0;i<game.getMap().getHatchSectors().size();i++) {		
			if(!game.getMap().getSector(game.getMap().getHatchSectors().get(i)).isClosed())
				return true;
		}
		return false;
	}
	

}
