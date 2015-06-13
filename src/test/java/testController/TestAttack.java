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
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;
/**
 * This class tests if a player do correctly an attack move.
 * 
 * @author Filippo
 *
 */
public class TestAttack {

	 Game model;
	 Player human;
	 Player alien;
	 Player alien2;

/**
 * before all the tests, there are a human and an alien in sector (7,3) of GalileiMap
 * 
 * @throws NumberFormatException
 * @throws IOException
 */
	@Before
	public  void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8, MapType.HEXAGONAL);

		Sector currentSector = model.getMap().getSector(new Coordinate (7,3));
		
		human = model.getPlayers(0);
		human.setPlayerType(PlayerType.HUMAN);
		human.setSpeed(1);
		human.setSector(currentSector);
		currentSector.addPlayer(human);

		alien = model.getPlayers(1);
		alien.setPlayerType(PlayerType.ALIEN);
		alien.setSpeed(2);
		alien.setSector(currentSector);
		currentSector.addPlayer(alien);
	}

	
	
	/**
	 *  The test verifies that a player attacked (e.g human) is effectively eliminated
	 */
	@Test
	public void testPlayerKilled(){
		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertFalse(human.isAlive());
	}
	/**
	 * The test verifies that an alien, after killing a human, is fed (he can move through 3 sectors)
	 */
	@Test
	public void testAlienSetFed() {
		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertEquals(alien.getSpeed(),3);	
	}
	
	/**
	 * test verifies that a human, after killing an alien, is not fed;
	 */
	@Test
	public void testHumanNotSetFed(){
		GameStatus status = new GameStatus(model, human);
		new Attack(status).attackMove();
		assertEquals(human.getSpeed(),1);	
	}
	/**
	 * test verifies that an alien, after killing another alien, is not fed;
	 */
	@Test
	public void testAlienNotSetFed(){
		GameStatus status = new GameStatus(model, alien);
		Sector currentSector = model.getMap().getSector(new Coordinate (8,4));
		alien.setSector(currentSector);
		
		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);
		alien2.setSpeed(2);
		alien2.setSector(currentSector);
		currentSector.addPlayer(alien2);
		
		new Attack(status).attackMove();
		assertEquals(alien.getSpeed(),2);	
	}
	/**
	 * test verifies that if a human is attacked, but he has 
	 * defense item card, he is not eliminated
	 */
	@Test 
	public void testIsDefendable(){
		GameStatus status = new GameStatus(model, alien);
		ItemCard itemCard = new ItemCard(ItemCardType.DEFENSE);
		human.addItem(itemCard);
		new Attack(status).attackMove();
			
		assertTrue(human.isAlive());
	}
	
	/**
	 * test verifies that if a player has not already moved,
	 * he can not do an action Attack
	 */
	@Test
	public void testNotMoved(){
		GameStatus status = new GameStatus(model, alien);
		status.setMoved(false);
		status.setAttacked(false);
		new Attack(status).doAction(new DTOTurn(null, null, null));
		
		assertFalse(status.isAttacked());
	}
	
	/**
	 * test verifies that if a player has  already attacked,
	 * he can not do another action Attack, even if he has already moved
	 */
	@Test
	public void testAlreadyAttacked(){
		GameStatus status = new GameStatus(model, alien);
		status.setMoved(true);
		status.setAttacked(true);
		new Attack(status).doAction(new DTOTurn(null, null, null));
		
		assertTrue(human.isAlive());
	}
	
	
	
	
	
	

	

}
