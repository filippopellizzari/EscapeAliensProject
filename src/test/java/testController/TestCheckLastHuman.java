package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import model.Game;
import model.PlayerState;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.CheckLastHuman;
import creator.GameCreator;
import model.*;
/**
 * This class tests that if an alien kills the last human in game,
 * he is winner
 * 
 * @author Filippo
 *
 */
public class TestCheckLastHuman {

	Game model;
	Player alien1;
	Player alien2;
	Player human1;
	Player human2;
	Player human3;
	
	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 5,
				MapType.HEXAGONAL);
		
		alien1 = model.getPlayers(0);
		alien1.setPlayerType(PlayerType.ALIEN);
		
		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);
		
		human1 = model.getPlayers(1);
		human1.setPlayerType(PlayerType.HUMAN);
		
		human2 = model.getPlayers(3);
		human2.setPlayerType(PlayerType.HUMAN);
		
		human3 = model.getPlayers(4);
		human3.setPlayerType(PlayerType.HUMAN);
		
	}

	/**
	 * test verifies that alien1 don't win if eliminates alien2
	 */
	@Test
	public void testAllHumansOutButAttackedIsAlien(){
		alien1.setInGame(true);
		alien1.setPlayerState(PlayerState.PLAYING);
		alien2.setInGame(false);
		alien2.setPlayerState(PlayerState.KILLED);
		
		human1.setInGame(false);
		human1.setPlayerState(PlayerState.KILLED);
		human2.setInGame(false);
		human2.setPlayerState(PlayerState.WINNER);
		human3.setInGame(false);
		human3.setPlayerState(PlayerState.KILLED);
		
		new CheckLastHuman(alien1, alien2, model).check();
		
		assertEquals(alien1.getPlayerState(), PlayerState.PLAYING);	
	}
	/**
	 * test verifies that alien1 don't win if eliminates human1, but human 2
	 * is still in game
	 */
	@Test
	public void testNotAllHumansOutAndAttackedIsHuman(){
		alien1.setInGame(true);
		alien1.setPlayerState(PlayerState.PLAYING);
		alien2.setInGame(true);
		alien2.setPlayerState(PlayerState.PLAYING);
		
		human1.setInGame(false);
		human1.setPlayerState(PlayerState.KILLED);
		human2.setInGame(true);
		human2.setPlayerState(PlayerState.PLAYING);
		human3.setInGame(false);
		human3.setPlayerState(PlayerState.WINNER);
		
		new CheckLastHuman(alien1, human1, model).check();
		
		assertEquals(alien1.getPlayerState(), PlayerState.PLAYING);	
	}
	/**
	 * test verifies that if player attacking is human1, his state is not set to winner, because
	 * is in the game and not all humans are out
	 */
	@Test
	public void testHumanAttack(){
		alien1.setInGame(true);
		alien1.setPlayerState(PlayerState.PLAYING);
		alien2.setInGame(true);
		alien2.setPlayerState(PlayerState.PLAYING);
		
		human1.setInGame(true);
		human1.setPlayerState(PlayerState.PLAYING);
		human2.setInGame(false);
		human2.setPlayerState(PlayerState.KILLED);
		human3.setInGame(false);
		human3.setPlayerState(PlayerState.WINNER);
		
		new CheckLastHuman(human1, human2, model).check();
		
		assertEquals(human1.getPlayerState(), PlayerState.PLAYING);	
	}
	/**
	 * test verifies that if alien1 attack human1 and there are not 
	 * other human players in game, alien1 is set to winner
	 */
	@Test
	public void testAlienWin(){
		alien1.setInGame(true);
		alien1.setPlayerState(PlayerState.PLAYING);
		alien2.setInGame(true);
		alien2.setPlayerState(PlayerState.PLAYING);
		
		human1.setInGame(false);
		human1.setPlayerState(PlayerState.KILLED);
		human2.setInGame(false);
		human2.setPlayerState(PlayerState.WINNER);
		human3.setInGame(false);
		human3.setPlayerState(PlayerState.WINNER);
		
		new CheckLastHuman(alien1, human1, model).check();
		
		assertEquals(alien1.getPlayerState(), PlayerState.WINNER);	
	}
	

}
