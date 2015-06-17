package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import model.*;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.CompleteTurn;
import controller.GameStatus;
import controller.Turn;
import creator.GameCreator;
/**
 * 
 * This class tests if a turn is completed correctly, based on status
 * of the player
 * 
 * @author Filippo
 *
 */
public class TestCompleteTurn {

	Game model;
	Player player;
	GameStatus status;
	Turn currentTurn;

	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
		
	}

	/**
	 * tests verifies that all conditions are satisfied
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testNoObligedActions() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		currentTurn = new Turn(model, player);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setMustDiscardItem(false);
		status.setMustDraw(false);
		status.setMustNoise(false);
		
		CompleteTurn ct = new CompleteTurn(currentTurn);
		ct.completeTurn();
		
		assertEquals(status, ct.getStatus());
	}
	/**
	 * test verifies that if a player has not already moved,
	 * after complete turn he is moved
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	@Test
	public void testNotMoved() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Coordinate c = new Coordinate(2,6);
		Sector s = model.getMap().getSector(c);
		s.addPlayer(player);
		player.setSector(s);
		
		currentTurn = new Turn(model, player);
		status = currentTurn.getStatus();
		status.setMoved(false);
		status.setMustDiscardItem(false);
		status.setMustDraw(false);
		status.setMustNoise(false);
		
		CompleteTurn ct = new CompleteTurn(currentTurn);
		ct.completeTurn();

		assertTrue(status.isMoved());
	}
	/**
	 * test verifies that if a player must discard an itemCard,
	 * after complete turn he discards one of his itemCards
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testMustDiscard() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ItemCard i1 = new ItemCard(ItemCardType.ADRENALINE);
		ItemCard i2 = new ItemCard(ItemCardType.ATTACK);
		ItemCard i3 = new ItemCard(ItemCardType.SEDATIVES);
		ItemCard i4 = new ItemCard(ItemCardType.DEFENSE);
		player.addItem(i1);
		player.addItem(i2);
		player.addItem(i3);
		player.addItem(i4);
		
		currentTurn = new Turn(model, player);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setMustDiscardItem(true);
		status.setMustDraw(false);
		status.setMustNoise(false);
		
		CompleteTurn ct = new CompleteTurn(currentTurn);
		ct.completeTurn();
		
		assertFalse(status.isMustDiscardItem());
	}
	/**
	 * test verifies that if a player must draw,
	 * after complete turn he has drawn
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testMustDraw() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ItemCard i1 = new ItemCard(ItemCardType.ADRENALINE);
		ItemCard i2 = new ItemCard(ItemCardType.ATTACK);
		ItemCard i3 = new ItemCard(ItemCardType.SEDATIVES);
		player.addItem(i1);
		player.addItem(i2);
		player.addItem(i3);
		
		currentTurn = new Turn(model, player);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setMustDiscardItem(false);
		status.setMustDraw(true);
		status.setMustNoise(false);
		
		CompleteTurn ct = new CompleteTurn(currentTurn);
		ct.completeTurn();
		
		assertFalse(status.isMustDraw());
	}
	/**
	 * test verifies that if a player must select a coordinate
	 * for noise in any sector, after complete turn he has done
	 * noise in a selected sector
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testMustNoise() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		currentTurn = new Turn(model, player);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setMustDiscardItem(false);
		status.setMustDraw(false);
		status.setMustNoise(true);
		
		CompleteTurn ct = new CompleteTurn(currentTurn);
		ct.completeTurn();
		
		assertFalse(status.isMustNoise());
	}
	
	
	

}
