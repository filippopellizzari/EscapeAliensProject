package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
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

	Game model;
	Player human;
	Player alien;
	Player alienFed;

	/**
	 * before all the tests, there are a human, an alien, and an alien fed
	 * (which has eliminated at least one human), in map Galilei
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);

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
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);
		s.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
		

		Coordinate dest = new Coordinate(12, 3);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that an alien can move across only one sector
	 */
	@Test
	public void testAlienMoveOne() {
		Coordinate curr = new Coordinate(6, 2);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate dest = new Coordinate(6, 3);
		assertTrue(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that an alien can move across two sectors
	 */
	@Test
	public void testAlienMoveTwo() {
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);

		GameStatus status = new GameStatus(model, alien);
		
		Coordinate dest = new Coordinate(12, 3);
		assertTrue(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that an alien can not move to an escape hatch sector
	 **/

	@Test
	public void testAlienMoveToHatch() {
		Coordinate curr = new Coordinate(3, 3);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);

		GameStatus status = new GameStatus(model, alien);
		
		Coordinate dest = new Coordinate(2, 2);
		assertFalse(new Move(status).moveCheck(dest));

	}

	/**
	 * test verifies that a human can move to an escape hatch sector
	 */
	@Test
	public void testHumanMoveToHatch() {
		Coordinate curr = new Coordinate(3, 3);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);
		s.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
		

		Coordinate dest = new Coordinate(2, 2);
		assertTrue(new Move(status).moveCheck(dest));

	}

	/**
	 * The test verifies that an alien "fed" can move through 3 sectors
	 * 
	 */
	@Test
	public void testAlienFedMoveThree() {
		Coordinate curr = new Coordinate(6, 3);
		Sector s = model.getMap().getSector(curr);
		alienFed.setSector(s);
		s.addPlayer(alienFed);
		
		GameStatus status = new GameStatus(model, alienFed);
		

		Coordinate dest = new Coordinate(9, 5);
		assertTrue(new Move(status).moveCheck(dest));
	}
	
	@Test
	public void testAlienFedMoveTwo() {
		Coordinate curr = new Coordinate(6, 9);
		Sector s = model.getMap().getSector(curr);
		alienFed.setSector(s);
		s.addPlayer(alienFed);

		GameStatus status = new GameStatus(model, alienFed);
		
		Coordinate dest = new Coordinate(6, 11);
		assertTrue(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that a generic player ( eg . human ) can not reach a
	 * null sector on the map ( even if he respects the distance )
	 */
	@Test
	public void testMoveToNull() {
		Coordinate curr = new Coordinate(5, 5);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);
		s.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
	

		Coordinate dest = new Coordinate(4, 4);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * the test verifies that a generic player (eg . alien ) can not reach a
	 * sector of the map across null sectors(even if it respects the
	 * distance)
	 */
	@Test
	public void testMoveThroughNull() {
		Coordinate curr = new Coordinate(5, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate dest = new Coordinate(3, 4);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * the test verifies that a generic player (eg . alien) can not reach a
	 * sector " closed " (eg. human sector) , even if it respects the distance
	 */
	@Test
	public void testMoveToClosedSector() {
		Coordinate curr = new Coordinate(14, 9);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
	

		Coordinate dest = new Coordinate(12,8);
		assertFalse(new Move(status).moveCheck(dest));
	}

	/**
	 * The test verifies that a generic player (eg. alien) can not reach a
	 * sector of the map moving across a sector " closed " (eg. human sector) ,
	 * even if it respects the distance
	 */
	@Test
	public void testMoveThroughClosedHumanSector() {
		Coordinate curr = new Coordinate(13, 8);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate dest = new Coordinate(11, 9);
		assertFalse(new Move(status).moveCheck(dest));
	}
	
	/**
	 * The test verifies that a generic player (eg. alien) can not reach a
	 * sector of the map moving across a sector " closed " (eg. hatch sector closed) ,
	 * even if it respects the distance
	 */
	@Test
	public void testMoveThroughClosedHatchSector() {
		Coordinate curr = new Coordinate(3, 3);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		s.addPlayer(alien);
		
		Coordinate hatch = new Coordinate(2,2);
		Sector hatchSector = model.getMap().getSector(hatch);
		human.setSector(hatchSector);
		hatchSector.addPlayer(human);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate dest = new Coordinate(1, 2);
		assertFalse(new Move(status).moveCheck(dest));
	}


	/**
	 * test verifies that a player who draw a green hatch card wins(he is not in
	 * the game anymore)
	 */
	@Test
	public void testDrawHatchCardGreen() {
		model.getHatchCards().getDeck()
				.add(0, new HatchCard(HatchCardColor.GREEN));
		GameStatus status = new GameStatus(model, alien);
		new Move(status).drawHatchCard();
		assertFalse(status.getPlayer().isInGame());
	}

	/**
	 * test verifies that a player who draw a red hatch card does not win (he
	 * remains in game)
	 */
	@Test
	public void testDrawHatchCardRed() {
		model.getHatchCards().getDeck()
				.add(0, new HatchCard(HatchCardColor.RED));
		GameStatus status = new GameStatus(model, alien);
		new Move(status).drawHatchCard();
		assertTrue(status.getPlayer().isInGame());
	}

	/**
	 * test verifies that a player who has already moved can not move, even if
	 * destination coordinate is right (he remains in the same position)
	 */
	@Test
	public void testDoActionMoveAlreadyMoved() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		
		status.setMoved(true);

		DTOTurn dtoTurn = new DTOTurn(new Coordinate(12, 3), null, null);
		new Move(status).doAction(dtoTurn);
		assertEquals(status.getPlayer().getSector(), start);
	}

	/**
	 * test verifies that a player who has not moved can do the move action, but
	 * it is not valid
	 */
	@Test
	public void testDoActionMoveNotMovedNotValid() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
		

		Coordinate destCoord = new Coordinate(12, 3);
		DTOTurn dtoTurn = new DTOTurn(destCoord, null, null);

		new Move(status).doAction(dtoTurn);
		assertEquals(status.getPlayer().getSector(), start);
	}

	/**
	 * test verifies that a player who has not moved can do the move action, and
	 * it is valid
	 */
	@Test
	public void testDoActionMoveNotMovedValid() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
	

		Coordinate destCoord = new Coordinate(12, 3);
		Sector dest = model.getMap().getSector(destCoord);
		DTOTurn dtoTurn = new DTOTurn(destCoord, null, null);
		new Move(status).doAction(dtoTurn);
		assertEquals(status.getPlayer().getSector(), dest);
	}

	/**
	 * test verifies that a player move effectively to destination
	 * 
	 *
	 **/
	@Test
	public void testMoveSectorDest() {
		Coordinate curr = new Coordinate(12, 6);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate destCoord = new Coordinate(12, 5);
		Sector dest = model.getMap().getSector(destCoord);

		new Move(status).move(destCoord);
		assertTrue(status.getPlayer().getSector() == dest);

	}

	/**
	 * test verifies that when an alien moves to a dangerous sector he is
	 * obliged to draw
	 */
	@Test
	public void testMoveToDangerousNotSedated() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate destCoord = new Coordinate(12, 3);

		new Move(status).move(destCoord);
		assertTrue(status.isMustDraw());

	}

	/**
	 * test verifies that when a human moves to a dangerous sector and he is
	 * sedated, he is not obliged to draw
	 */
	@Test
	public void testMoveToDangerousSedated() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
		

		Coordinate destCoord = new Coordinate(13, 6);

		status.setSedated(true);

		new Move(status).move(destCoord);
		assertFalse(status.isMustDraw());

	}

	/**
	 * test verifies that a player who moves to a secure sector is not obliged
	 * to do nothing
	 */
	@Test
	public void testMoveToSecure() {
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);

		GameStatus status = new GameStatus(model, alien);
		
		Coordinate destCoord = new Coordinate(12, 4);

		new Move(status).move(destCoord);
		assertFalse(status.isMustDraw());

	}

	/**
	 * test move to hatch sector; verifies that the hatch is closed after
	 * passing over
	 */
	@Test
	public void testMoveToHatch() {
		Coordinate curr = new Coordinate(3, 3);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		GameStatus status = new GameStatus(model, alien);
		

		Coordinate destCoord = new Coordinate(2, 2);
		Sector dest = model.getMap().getSector(destCoord);

		new Move(status).move(destCoord);
		assertTrue(dest.isClosed());

	}

	/**
	 * test verifies that a human with adrenaline, after a move action, has not
	 * more adrenaline
	 */
	@Test
	public void testMoveAdrenalineEliminated() {
		Coordinate curr = new Coordinate(3, 3);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		GameStatus status = new GameStatus(model, human);
	

		human.setSpeed(2);

		Coordinate destCoord = new Coordinate(5, 4);

		new Move(status).move(destCoord);
		assertEquals(human.getSpeed(), 1);
	}

}
