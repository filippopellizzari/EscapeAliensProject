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
 * before all the tests, there are a human, an alien, and an alien fed (which has eliminated
 * at least one human), in map Galilei
 * 
 * @throws NumberFormatException
 * @throws IOException
 */
	@Before
	public  void always() throws NumberFormatException, IOException{
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
	 * The test verifies that an alien can move across only one sector 
	 */
	@Test
	public void testAlienMoveOne() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);

		Coordinate dest = new Coordinate(12, 4);
		assertTrue(new Move(status).moveCheck(dest));
	}
	
	/**
	 * The test verifies that an alien can move across two sectors 
	 */
	@Test
	public void testAlienMoveTwo() {
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
	 * test verifies that a human can move to an escape hatch sector
	 */
	@Test
	public void testHumanMoveToHatch() {
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(3, 3);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);

		Coordinate dest = new Coordinate(2, 2);
		assertTrue(new Move(status).moveCheck(dest));

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
		assertEquals(status.getPlayer().getSector(),start);
	}
	/**
	 * test verifies that a player who has not moved can do
	 * the move action, but it is not valid
	 */
	@Test
	public void testDoActionMoveNotMovedNotValid(){
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		Coordinate destCoord = new Coordinate(12,3);
		DTOTurn dtoTurn = new DTOTurn(destCoord, null, null);
		
		new Move(status).doAction(dtoTurn);
		assertEquals(status.getPlayer().getSector(),start);
	}
	/**
	 * test verifies that a player who has not moved can do
	 * the move action, and it is valid
	 */
	@Test
	public void testDoActionMoveNotMovedValid(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		Coordinate destCoord = new Coordinate(12,3);
		Sector dest = model.getMap().getSector(destCoord);
		DTOTurn dtoTurn = new DTOTurn(destCoord, null, null);
		new Move(status).doAction(dtoTurn);
		assertEquals(status.getPlayer().getSector(),dest);
	}
	
	/**
	 * test verifies that a player move effectively to 
	 * destination
	 *
	 **/
	@Test
	public void testMoveSectorDest(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
	
		Coordinate destCoord = new Coordinate(12,3);
		Sector dest = model.getMap().getSector(destCoord);
		
		new Move(status).move(destCoord);
		assertTrue(status.getPlayer().getSector()==dest);
		
	}
	/**
	 * test verifies that when an alien moves to a dangerous sector
	 *  he is obliged to draw
	 */
	@Test
	public void testMoveToDangerousNotSedated(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		Coordinate destCoord = new Coordinate(12,3);
		
		new Move(status).move(destCoord);
		assertTrue(status.isMustDraw());
		
	}
	/**
	 * test verifies that when a human moves to a dangerous sector
	 * and he is sedated, he is not obliged to draw
	 */
	@Test
	public void testMoveToDangerousSedated(){
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		Coordinate destCoord = new Coordinate(13,6);
		
		status.setSedated(true);
		
		new Move(status).move(destCoord);
		assertFalse(status.isMustDraw());
		
	}
	
	/**
	 * test verifies that a player who moves to a secure sector
	 * is not obliged to do nothing
	 */
	@Test
	public void testMoveToSecure(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12, 5);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		Coordinate destCoord = new Coordinate(12,4);
		
		new Move(status).move(destCoord);
		assertFalse(status.isMustDraw());
		
	}
	/**
	 * test move to hatch sector; verifies that the hatch 
	 * is closed after passing over
	 */
	@Test
	public void testMoveToHatch(){
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(3,3);
		Sector start = model.getMap().getSector(curr);
		alien.setSector(start);
		start.addPlayer(alien);
		
		Coordinate destCoord = new Coordinate(2,2);
		Sector dest = model.getMap().getSector(destCoord);
		
		new Move(status).move(destCoord);
		assertTrue(dest.isClosed());
		
	}
	/**
	 * test verifies that a human with adrenaline, after a move action, 
	 *has not more adrenaline
	 */
	@Test
	public void testMoveAdrenalineEliminated(){
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(3,3);
		Sector start = model.getMap().getSector(curr);
		human.setSector(start);
		start.addPlayer(human);
		
		human.setSpeed(2);
		
		Coordinate destCoord = new Coordinate(5,4);
		
		new Move(status).move(destCoord);
		assertEquals(human.getSpeed(),1);
	}
	
	
	
	

}
