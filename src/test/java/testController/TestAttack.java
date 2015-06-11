package testController;
import model.*;
import controller.*;
import dto.*;
import static org.junit.Assert.*;

import java.io.IOException;

import model.Game;
import model.Player;
import model.PlayerType;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;
/**
 * This class tests if a player do correctly an attack move.
 * 
 * @author filippopellizzari
 *
 */
public class TestAttack {

	GameStatus statusAlien;

	
	static Game model;
	static Player human;
	static Player alien;


	@BeforeClass
	public static void onlyOnce() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8, MapType.HEXAGONAL);

		Sector currentSector = model.getMap().getSector(new Coordinate (7,3));
		
		model.getPlayers(0).setPlayerType(PlayerType.HUMAN);
		model.getPlayers(0).setSpeed(1);
		human = model.getPlayers(0);
		human.setSector(currentSector);
		currentSector.addPlayer(human);

		model.getPlayers(1).setPlayerType(PlayerType.ALIEN);
		model.getPlayers(1).setSpeed(2);
		alien = model.getPlayers(1);
		alien.setSector(currentSector);
		currentSector.addPlayer(alien);
	}

	
	
	/**
	 *  The test verifies that a player attacked (e.g human) is effectively eliminated
	 */
	@Test
	public void testPlayerKilled(){
		statusAlien = new GameStatus(model, alien);
		new Attack(statusAlien).attackMove();
		assertFalse(human.isAlive());
	}
	/**
	 * The test verifies that an alien, after killing a human, is fed (he can move through 3 sectors)
	 */
	@Test
	public void testSetFed() {
		statusAlien = new GameStatus(model, alien);
		new Attack(statusAlien).attackMove();
		assertEquals(alien.getSpeed(),3);	
	}
	/**
	 * the test verifies that, after the attack, dtoGame contains type of the player killed (human, in this case) and 
	 * his position(coordinate(7,3) in this case)
	 */
	@Test
	public void testDtoPlayerType(){
		statusAlien = new GameStatus(model, alien);
		DTOGame dtoGame = new Attack(statusAlien).attackMove();
		assertEquals(dtoGame.getPlayerType()[0],PlayerType.HUMAN);
	}
	

	

}
