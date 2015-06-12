package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import creator.*;
import model.*;
import connection.MapName;
import connection.MapType;
import controller.*;
import dto.DTOTurn;

/**
 * Test of move check and action move of players(eg. Galilei Map)
 * 
 * @author Filippo
 *
 */
public class TestMove {

	static Game model;
	static Player human;
	static Player alien;
	static Player alienFed; // alieno che è riuscito ad eliminare almeno un
							// umano

	@BeforeClass
	public static void onlyOnce() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8, MapType.HEXAGONAL);

		model.getPlayers(0).setPlayerType(PlayerType.HUMAN);
		model.getPlayers(0).setSpeed(1);
		human = model.getPlayers(0);

		model.getPlayers(1).setPlayerType(PlayerType.ALIEN);
		model.getPlayers(1).setSpeed(2);
		alien = model.getPlayers(1);

		model.getPlayers(2).setPlayerType(PlayerType.ALIEN);
		model.getPlayers(2).setSpeed(3);
		alienFed = model.getPlayers(2);
	}

	/**
	 * The test verifies that a human can not move across two sectors ( not
	 * using adrenaline )
	 */
	@Test
	public void testHumanMove() {
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);

		Coordinate dest = new Coordinate(12, 3);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that an alien can move across two sectors ( has not
	 * eliminated anyone)
	 */
	@Test
	public void testAlienMove() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(12, 3);
		assertTrue(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that an alien can not move to an escape hatch sector
	 **/

	@Test
	public void testAlienMoveToHatch() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(4, 3);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(2, 2);
		assertFalse(new Move(status).moveCheck(dest));

	}

	/**
	 * The test verifies that an alien "fed" can move through 3 sectors
	 * 
	 */
	@Test
	public void testAlienFedMove() {
		GameStatus status = new GameStatus(model, alienFed);
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		alienFed.setSector(s);

		Coordinate dest = new Coordinate(13, 6);
		assertTrue(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that a generic player ( eg . Alien ) can not reach a
	 * null sector on the map ( even if he respects the distance )
	 */
	@Test
	public void testMoveToNull() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(6, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(4, 4);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * the test verifies that a generic player (eg . alien ) can not reach a
	 * sector of the map across null sectors " null " (even if it respects the
	 * distance)
	 */
	@Test
	public void testMoveThroughNull() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(5, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(3, 4);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * the test verifies that a generic player (eg . alien) can not reach a
	 * sector " closed " (eg. human sector) , even if it respects the distance
	 */
	@Test
	public void testMoveToClosedSector() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(14, 9);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = model.getMap().getHumanCoord();
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that a generic player (eg. alien) can not reach a
	 * sector of the map moving across a sector " closed " (eg. human sector) ,
	 * even if it respects the distance
	 */
	@Test
	public void testMoveThroughClosedSector() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(13, 8);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(11, 9);
		assertFalse(new Move(status).moveCheck(dest));
	}
	
	/**
	 * test verifies that a player who draw a green hatch card wins(he is not
	 * in the game anymore)
	 */
	@Test
	public void  testDrawHatchCardGreen(){
		model.getHatchCards().getDeck().add(0, new HatchCard(HatchCardColor.GREEN));
		GameStatus status = new GameStatus(model, alien);
		new Move(status).drawHatchCard();
		assertFalse(status.getPlayer().isAlive());
	}
	/**
	 * test verifies that a player who draw a red hatch card does not win
	 * (he remains in game)
	 */
	@Test
	public void  testDrawHatchCardRed(){
		model.getHatchCards().getDeck().add(0, new HatchCard(HatchCardColor.RED));
		GameStatus status = new GameStatus(model, alien);
		new Move(status).drawHatchCard();
		assertTrue(status.getPlayer().isAlive());
	}
	
	/**
	 * test verifies that a player who has already moved can not move,
	 * even if destination coordinate is right
	 * (he remains in the same position)
	 */
	@Test
	public void testDoActionMoveAlreadyMoved(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		status.setMoved(true);

		DTOTurn dtoTurn = new DTOTurn(new Coordinate(12,3), null, null);
		new Move(status).doAction(dtoTurn);
		assertTrue(status.getPlayer().getSector()==start);
	}
	

}
