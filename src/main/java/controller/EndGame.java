package controller;

import dto.DTOGame;
import model.Game;
import model.PlayerType;

/**
 * This class control if the game is finished; 
 * if it's true, deletes the game and disconnects all players
 * 
 * @author Filippo
 * @author Nicola
 */

public class EndGame {
	
	private final Game game;
	private final int round;
	private DTOGame dtoGame;
	private final int TOT_ROUNDS = 39;

	public EndGame(Game game, int round){
		this.game = game;
		this.round = round;	
		this.dtoGame = new DTOGame();
	}
	
	public boolean control() {
		return (round > TOT_ROUNDS) || allHumansDead() || allHatchClosed();
	}
	
	public boolean allHumansDead(){//controllo umani vivi
		for(int i=0;i<game.getPlayers().length;i++) {			
			if(game.getPlayers(i).getType().equals(PlayerType.HUMAN)
					&& game.getPlayers(i).isAlive()) 
				return false;
		}
		return true;
	}
	
	private boolean allHatchClosed(){//controllo portelloni
		for(int i=0;i<game.getMap().getHatchSectors().size();i++) {		
			if(!game.getMap().getSector(game.getMap().getHatchSectors().get(i)).isClosed())
				return false;
		}
		return true;
	}
	

}
