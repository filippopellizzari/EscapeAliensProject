package testCreator;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
/**
 * @author Nicola
 * @author Filippo
 *
 */
public class TestCreatorGame {
	static Game game;
	static GameCreator gameCreator;

	
	@BeforeClass public static void onlyOnce() throws NumberFormatException, IOException {
		 
		
		gameCreator=GameCreator.getinstance();
		game=gameCreator.createGame("Fermi", 3, "Hexagonal"); 
		
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
	public void testTypeOfPlayer() {				//test correct number of type of player, class playerCreator
		int alien=0;
		int human=0;
		for(int i=0;i<3;i++)
		{
			if(game.getPlayers(i).getType()==PlayerType.ALIEN) alien++;
			if(game.getPlayers(i).getType()==PlayerType.HUMAN) human++;
		}
		assertTrue(alien==2 && human==1);
	}
	
	@Test
	public void testCorrectPlayers() {				//test correct type of player and its features, class playerCreator
		Player player;
		for(int i=0;i<3;i++)
		{
			player=game.getPlayers(i);
			if(player.getType()==PlayerType.ALIEN)
				assertTrue(player.getSector()==game.getMap().getSector(game.getMap().getAlienCoord()) 
				&& player.getSpeed()==2 && player.isAlive()==true && player.getItem().isEmpty()==true);
			else 
				assertTrue(player.getSector()==game.getMap().getSector(game.getMap().getHumanCoord()) 
					&& player.getSpeed()==1 && player.isAlive()==true && player.getItem().isEmpty()==true);
		}
	}	

	
	
}

