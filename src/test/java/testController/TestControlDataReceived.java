package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.ControlDataReceived;
import creator.GameCreator;
import dto.DTOSend;
import model.*;
/**
 * this class tests that the first control on data sent and on 
 * player playing in the turn is done correctly
 * 
 * @author Filippo
 *
 */
public class TestControlDataReceived {

	Game model;
	Player player;
	DTOSend dtoSend;
	String s;

	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
	}

	/**
	 * test verifies that if a player try to do an action, sending a message,
	 * but it's not his turn, he cannot do nothing
	 */
	@Test
	public void testWrongTurn() {
		int numberPlayerTrying = 0;
		dtoSend = new DTOSend(null, numberPlayerTrying, null, null,
				null);
		int numberPlayer = 1;
		s = new ControlDataReceived(dtoSend, model, numberPlayer)
				.verify();

		assertTrue(s != null);
	}
	/**
	 * test verifies that a player out of game can not do actions
	 */
	@Test
	public void testOutOfGame(){
		player.setInGame(false);
		
		dtoSend = new DTOSend(null, player.getNumber(), null, null,null);
		s = new ControlDataReceived(dtoSend, model, player.getNumber())
		.verify();
		
		assertTrue(s != null);	
	}
	/**
	 * test verifies that if a coordinate is not in the map, player 
	 * can not do the action involving coordinates
	 */
	@Test 
	public void testWrongCoordinates(){
		Coordinate coord = new Coordinate (4,4);
		dtoSend = new DTOSend(coord, player.getNumber(), null, null,null);
		s = new ControlDataReceived(dtoSend, model, player.getNumber())
		.verify();
		
		assertTrue(s != null);
	}
	
	/**
	 * test verifies that if a player is alive and choose 
	 * coordinates in the map, he can do the action involving coordinates
	 */
	@Test 
	public void testRightCoordinates(){
		Coordinate coord = new Coordinate (4,3);
		player.setInGame(true);
		
		dtoSend = new DTOSend(coord, player.getNumber(), null, null,null);
		s = new ControlDataReceived(dtoSend, model, player.getNumber())
		.verify();
		
		assertTrue(s == null);
	}
	/**
	 * test verifies that if a player has not a particular itemCard,
	 * he can not do the action involving itemCard
	 */
	@Test 
	public void testWrongCardType(){
		player.addItem(new ItemCard(ItemCardType.SEDATIVES));
		ItemCardType type = ItemCardType.ATTACK;
		
		dtoSend = new DTOSend(null, player.getNumber(), type, null,null);
		s = new ControlDataReceived(dtoSend, model, player.getNumber())
		.verify();
		
		assertTrue(s != null);
	}
	/**
	 * test verifies that if a player has a particular itemCard,
	 * he can do the action involving itemCard
	 */
	@Test
	public void testRightCardType(){
		player.addItem(new ItemCard(ItemCardType.SEDATIVES));
		ItemCardType type = ItemCardType.SEDATIVES;
		
		dtoSend = new DTOSend(null, player.getNumber(), type, null,null);
		s = new ControlDataReceived(dtoSend, model, player.getNumber())
		.verify();
		
		assertTrue(s == null);
	}

}
