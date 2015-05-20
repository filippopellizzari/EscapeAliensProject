package testCreator;

import static org.junit.Assert.*;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCreatorGame {
	static Game game;
	static GameCreator gameCreator;
	
	@BeforeClass public static void onlyOnce() {
		gameCreator=GameCreator.getinstance();
		game=gameCreator.createGame("FermiMap", 3, "Exagonal");
	}
	
	@Test
	public void testCreateGame() {
		assertTrue(game instanceof Game);				//test correct type of game, class game
	}
	
	@Test
	public void testHatchCards() {
		assertTrue(game.getHatchCards().getDeck().size()==6 && game.getHatchCards().getDiscardPile().isEmpty()==true);		//test number of hatch cards, class Game
	}
	
	@Test
	public void testSectorCards() {
		assertTrue(game.getSectorCards().getDeck().size()==25 && game.getSectorCards().getDiscardPile().isEmpty()==true);	//test number of sector cards, class Game
	}
	
	@Test
	public void testItemCards() {
		assertTrue(game.getItemCards().getDeck().size()==12 && game.getItemCards().getDiscardPile().isEmpty()==true);		//test number of item cards, class Game
	}
	
	@Test
	public void testTakeTheWrongPlayer() {			//test number of player, class Game
		assertTrue(game.getPlayers(3)==null);
	}
	
	@Test
	public void testTypeOfPlayer() {				//test correct number of type of player, class playerCreator
		int alien=0;
		int human=0;
		for(int i=0;i<3;i++)
		{
			if(game.getPlayers(i).getPlayerType()==PlayerType.ALIEN) alien++;
			if(game.getPlayers(i).getPlayerType()==PlayerType.HUMAN) human++;
		}
		assertTrue(alien==2 && human==1);
	}
	
	@Test
	public void testCorrectPlayers() {				//test correct type of player and its features, class playerCreator
		Player player;
		for(int i=0;i<3;i++)
		{
			player=game.getPlayers(i);
			if(player.getPlayerType()==PlayerType.ALIEN)
				assertTrue(player.getCurrentSector()==game.getMap().getSector(game.getMap().getAlienSector()) 
				&& player.getSpeed()==2 && player.isAlive()==true && player.getItemCardPlayer().isEmpty()==true);
			else 
				assertTrue(player.getCurrentSector()==game.getMap().getSector(game.getMap().getHumanSector()) 
					&& player.getSpeed()==1 && player.isAlive()==true && player.getItemCardPlayer().isEmpty()==true);
		}
	}
}
