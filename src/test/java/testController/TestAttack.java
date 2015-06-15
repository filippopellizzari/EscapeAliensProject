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

	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);

		Sector currentSector = model.getMap().getSector(new Coordinate(7, 3));

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
	 * The test verifies that a player attacked (e.g human) is effectively
	 * eliminated
	 */
	@Test
	public void testPlayerKilled() {
		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertFalse(human.isAlive());
	}

	/**
	 * test verifies that two player attacked are effectively eliminated
	 */
	@Test
	public void testTwoPlayersKilled() {
		Sector currentSector = model.getMap().getSector(new Coordinate(7, 3));
		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);
		alien2.setSpeed(2);
		alien2.setSector(currentSector);
		currentSector.addPlayer(alien2);

		currentSector.addPlayer(currentSector.removePlayer());
		currentSector.addPlayer(currentSector.removePlayer());

		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertFalse(alien2.isAlive());
	}

	/**
	 * test verifies that a player does not attack himself
	 */
	@Test
	public void testNotSuicide() {
		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertTrue(alien.isAlive());
	}

	/**
	 * test verifies that a player eliminated discard all his itemCard (e.g 1
	 * sedatives, 2 adrenaline)
	 */
	@Test
	public void testRemoveAllItems() {
		GameStatus status = new GameStatus(model, alien);
		human.addItem(new ItemCard(ItemCardType.SEDATIVES));
		human.addItem(new ItemCard(ItemCardType.ADRENALINE));
		human.addItem(new ItemCard(ItemCardType.ADRENALINE));

		new Attack(status).attackMove();

		assertTrue(human.getItem().isEmpty());
	}

	/**
	 * The test verifies that an alien, after killing a human, is fed (he can
	 * move through 3 sectors)
	 */
	@Test
	public void testAlienSetFed() {
		GameStatus status = new GameStatus(model, alien);
		new Attack(status).attackMove();
		assertEquals(alien.getSpeed(), 3);
	}

	/**
	 * test verifies that a human, after killing an alien, is not fed;
	 */
	@Test
	public void testHumanNotSetFed() {
		GameStatus status = new GameStatus(model, human);
		new Attack(status).attackMove();
		assertEquals(human.getSpeed(), 1);
	}

	/**
	 * test verifies that an alien, after killing another alien, is not fed;
	 */
	@Test
	public void testAlienNotSetFed() {
		Sector current = model.getMap().getSector(new Coordinate(8, 4));

		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);
		alien2.setSpeed(2);
		alien2.setSector(current);
		current.addPlayer(alien2);

		alien.setSector(current);
		current.addPlayer(alien);

		GameStatus status = new GameStatus(model, alien);

		new Attack(status).attackMove();
		assertEquals(alien.getSpeed(), 2);
	}

	/**
	 * test verifies that if a human is attacked, but he has defense item card,
	 * he is not eliminated
	 */
	@Test
	public void testIsDefendable() {
		GameStatus status = new GameStatus(model, alien);
		human.addItem(new ItemCard(ItemCardType.DEFENSE));
		new Attack(status).attackMove();

		assertTrue(human.isAlive());
	}

	/**
	 * test verifies that if an alien has not already moved, he can not do an
	 * action Attack
	 */
	@Test
	public void testNotMoved() {
		GameStatus status = new GameStatus(model, alien);
		status.setMoved(false);
		status.setAttacked(false);
		new Attack(status).doAction(new DTOTurn(null, null, null));

		assertFalse(status.isAttacked());
	}

	/**
	 * test verifies that if an alien has already attacked, he can not do
	 * another action Attack, even if he has already moved
	 */
	@Test
	public void testAlreadyAttacked() {
		GameStatus status = new GameStatus(model, alien);
		status.setMoved(true);
		status.setAttacked(true);
		new Attack(status).doAction(new DTOTurn(null, null, null));

		assertTrue(human.isAlive());
	}

	/**
	 * test verifies that if an alien has already moved and has not already
	 * attacked, he can do action Attack
	 */
	@Test
	public void testCanAttack() {
		GameStatus status = new GameStatus(model, alien);
		status.setMoved(true);
		status.setAttacked(false);
		new Attack(status).doAction(new DTOTurn(null, null, null));

		assertTrue(status.isAttacked());
	}

}
