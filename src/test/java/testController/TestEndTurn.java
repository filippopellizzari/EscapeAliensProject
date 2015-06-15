package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.EndTurn;
import controller.GameStatus;
import creator.GameCreator;
import model.*;

/**
 * this class tests the validity of endTurn;
 * if a player has not moved or must draw or must discard  or must select a sector
 * can not end the turn.
 * 
 * @author Filippo
 *
 */
public class TestEndTurn {

	Game model;
	Player player;
	GameStatus status;
	
	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
	}
	/**
	 * test verifies that a player not moved can not end the turn
	 */
	@Test
	public void testNotMoved() {
		status = new GameStatus(model, player);
		status.setMoved(false);
		assertFalse(new EndTurn(status).isEndTurn());	
	}
	
	/**
	 * test verifies that a player obliged to draw can not end the turn
	 */
	@Test
	public void testMustDraw() {
		status = new GameStatus(model, player);
		status.setMoved(true);
		status.setMustDraw(true);
		assertFalse(new EndTurn(status).isEndTurn());	
	}
	
	/**
	 * test verifies that a player obliged to discard an itemCard
	 * can not end the turn
	 */
	@Test
	public void testMustDiscardItem() {
		status = new GameStatus(model, player);
		status.setMoved(true);
		status.setMustDiscardItem(true);
		assertFalse(new EndTurn(status).isEndTurn());	
	}
	/**
	 * test verifies that a player obliged to select a sector for noise
	 * can not end the turn
	 */
	@Test
	public void testMustNoise() {
		status = new GameStatus(model, player);
		status.setMoved(true);
		status.setMustNoise(true);
		assertFalse(new EndTurn(status).isEndTurn());	
	}
	/**
	 * test verifies that a player who has completed all the actions
	 * following the rules can end the turn
	 */
	@Test
	public void testCanEndTurn(){
		status = new GameStatus(model, player);
		status.setMoved(true);
		status.setMustDraw(false);
		status.setMustDiscardItem(false);
		status.setMustNoise(false);
		assertTrue(new EndTurn(status).isEndTurn());	
	}
	
	

}
