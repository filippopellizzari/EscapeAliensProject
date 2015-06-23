package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import model.*;
import connection.*;
import dto.*;
import controller.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests functions of gameController, considering a GalileiMap and 6
 * players (3 aliens, 3 humans)
 * 
 * @author Filippo
 *
 */
public class TestGameController {

	GameController gc;
	DTOGame dtoGame;
	DTOSend dtoSend;
	Turn currentTurn;
	GameStatus status;
	Game model;
	Player alien1;
	Player alien2;
	Player alien3;
	Player human1;
	Player human2;
	Player human3;

	@Before
	public void always() throws NumberFormatException, IOException {
		gc = new GameController(MapName.Galilei, 6, MapType.HEXAGONAL);
		model = gc.getGame();

		alien1 = model.getPlayers(0);
		alien1.setPlayerType(PlayerType.ALIEN);

		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);

		alien3 = model.getPlayers(5);
		alien3.setPlayerType(PlayerType.ALIEN);

		human1 = model.getPlayers(1);
		human1.setPlayerType(PlayerType.HUMAN);

		human2 = model.getPlayers(3);
		human2.setPlayerType(PlayerType.HUMAN);

		human3 = model.getPlayers(4);
		human3.setPlayerType(PlayerType.HUMAN);

	}

	/**
	 * test verifies that when player 3 has finished his turn and player 4 is
	 * out of game, then next turn is assigned to player 5
	 */
	@Test
	public void testJumpTurn() {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(3);
		human3.setInGame(false);

		dtoGame = new DTOGame();
		gc.endTurn(dtoGame);

		assertEquals(gc.getCurrentNumberPlayer(), 5);
	}

	/**
	 * test verifies that when player 4 has finished turn and player 5 is out of
	 * game, next turn is assigned to player 0 and game jumps to the next round
	 * of game
	 */
	@Test
	public void testNewRound() {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(4);
		alien3.setInGame(false);

		dtoGame = new DTOGame();
		gc.endTurn(dtoGame);

		assertEquals(gc.getRound(), 4);
	}

	/**
	 * test verifies that when game is at the final round, then it finishes in
	 * favor of aliens and all are disconnected
	 */
	@Test
	public void testFinalRound() {
		gc.setRound(39);
		gc.setCurrentNumberPlayer(4);
		alien3.setInGame(false);

		dtoGame = new DTOGame();
		gc.endTurn(dtoGame);

		assertFalse(alien1.isInGame());
	}

	/**
	 * test verifies that game finishes correctly even if before final round
	 * (e.g: alien2 has eliminated the last human in game)
	 */
	@Test
	public void testEndBeforeFinalRound() {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(2);

		alien2.setPlayerState(PlayerState.WINNER);
		human1.setInGame(false);
		human2.setInGame(false);
		human3.setInGame(false);

		dtoGame = new DTOGame();
		gc.endTurn(dtoGame);

		assertFalse(alien1.isInGame());
	}

	/**
	 * test verifies that a turn is completed and ended correctly
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCompleteAndEndTurn() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(2);

		ItemCard i1 = new ItemCard(ItemCardType.ADRENALINE);
		ItemCard i2 = new ItemCard(ItemCardType.ATTACK);
		ItemCard i3 = new ItemCard(ItemCardType.SEDATIVES);
		ItemCard i4 = new ItemCard(ItemCardType.SEDATIVES);
		alien2.addItem(i1);
		alien2.addItem(i2);
		alien2.addItem(i3);
		alien2.addItem(i4);

		currentTurn = new Turn(model, alien2);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setMustDiscardItem(true);

		gc.setCurrentTurn(currentTurn);
		gc.completeTurn();

		assertFalse(status.isMustDiscardItem());
	}

	/**
	 * test verifies correct move action
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testMove() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(2);

		Coordinate c = new Coordinate(5, 2);
		Sector s = model.getMap().getSector(c);
		alien2.setSector(s);
		s.addPlayer(alien2);
		alien2.setSpeed(2);

		Coordinate dest = new Coordinate(7, 3);
		Sector destSector = model.getMap().getSector(dest);

		currentTurn = new Turn(model, alien2);
		status = currentTurn.getStatus();
		gc.setCurrentTurn(currentTurn);

		dtoSend = new DTOSend(dest, 2, null, ActionType.MOVE, null);
		gc.doAnAction(dtoSend);

		assertEquals(alien2.getSector(), destSector);
	}

	/**
	 * test verifies that the player can not do the move, because class control
	 * data received verified that destination coordinates are not available in
	 * the map Galilei
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testNullCoordinates() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(2);

		Coordinate c = new Coordinate(5, 2);
		Sector s = model.getMap().getSector(c);
		alien2.setSector(s);
		s.addPlayer(alien2);
		alien2.setSpeed(2);

		Coordinate dest = new Coordinate(4, 1);

		currentTurn = new Turn(model, alien2);
		status = currentTurn.getStatus();
		gc.setCurrentTurn(currentTurn);

		dtoSend = new DTOSend(dest, 2, null, ActionType.MOVE, null);
		gc.doAnAction(dtoSend);

		assertEquals(alien2.getSector(), s);
	}

	/**
	 * test verifies that alien2 attacks correclty human1
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testAttack() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		gc.setRound(3);
		gc.setCurrentNumberPlayer(2);

		Coordinate c = new Coordinate(5, 2);
		Sector s = model.getMap().getSector(c);
		human1.setSector(s);
		s.addPlayer(human1);
		alien2.setSector(s);
		s.addPlayer(alien2);
		alien2.setSpeed(2);

		currentTurn = new Turn(model, alien2);
		status = currentTurn.getStatus();
		status.setMoved(true);
		status.setAttacked(false);
		gc.setCurrentTurn(currentTurn);

		dtoSend = new DTOSend(null, 2, null, ActionType.ATTACK, null);
		gc.doAnAction(dtoSend);

		assertTrue(human1.getPlayerState().equals(PlayerState.KILLED));
	}
	/**
	 * test verifies that human1 use correcly an ItemCard (e.g. teleport)
	 * 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testUseItem() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		gc.setRound(3);
		gc.setCurrentNumberPlayer(1);
		
		ItemCard i1 = new ItemCard(ItemCardType.SEDATIVES);
		ItemCard i2 = new ItemCard(ItemCardType.TELEPORT);
		human1.addItem(i1);
		human1.addItem(i2);
		
		Coordinate c = new Coordinate(5, 2);
		Sector s = model.getMap().getSector(c);
		human1.setSector(s);
		s.addPlayer(human1);
		
		currentTurn = new Turn(model, human1);
		gc.setCurrentTurn(currentTurn);
		
		dtoSend = new DTOSend(null, 1, ItemCardType.TELEPORT, ActionType.USEITEM, null);
		gc.doAnAction(dtoSend);
		
		Coordinate humanCoord = model.getMap().getHumanCoord();
		
		assertEquals(human1.getSector().getCoordinate(), humanCoord);
	}
	/**
	 * test verifies that when a player wants to end a turn and has done all
	 * obliged actions, then can terminate turn
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testEndTurn() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		gc.setRound(3);
		gc.setCurrentNumberPlayer(5);
		
		currentTurn = new Turn(model, alien3);
		status = currentTurn.getStatus();
		status.setMoved(true);
		gc.setCurrentTurn(currentTurn);
		
		dtoSend = new DTOSend(null, 5, null, ActionType.ENDTURN, null);
		gc.doAnAction(dtoSend);
		
		assertEquals(gc.getCurrentNumberPlayer(),0);	
	}
	/**
	 * test verifies that getView send correctly the initial positions of game
	 * (e.g initial position of alien1)
	 */
	@Test
	public void testGetInitialPosition(){
		Coordinate c = new Coordinate(12,6);
		Sector s = model.getMap().getSector(c);
		s.addPlayer(alien1);
		alien1.setSector(s);
		
		ViewForPlayer [] v = gc.getViews();
		
		assertEquals(v[0].getCoordinate(), c);
	}
	/**
	 * test verifies that getView send correctly the type of a generic player
	 * (e.g alien)
	 */
	@Test
	public void testViewType(){
		ViewForPlayer [] v = gc.getViews();
		assertEquals(v[0].getPlayerType(), PlayerType.ALIEN);
	}

}
