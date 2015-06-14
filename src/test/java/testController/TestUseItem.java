package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;
import model.*;
import controller.*;
import dto.DTOGame;
import dto.DTOTurn;

/**
 * class tests the correct use of itemCards by a human player
 * 
 * @author Filippo
 *
 */
public class TestUseItem {

	Game model;
	Player player;
	GameStatus status;

	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
		player.setPlayerType(PlayerType.HUMAN);
	}

	/**
	 * test verifies the correct effect of Teleport itemCard
	 */
	@Test
	public void testTeleport() {
		Coordinate coord = new Coordinate(6, 1);
		Sector current = model.getMap().getSector(coord);
		player.setSector(current);
		current.addPlayer(player);
		status = new GameStatus(model, player);

		new UseItem(status).teleport();

		Coordinate human = new Coordinate(12, 8);

		assertEquals(player.getSector().getCoordinate(), human);
	}

	/**
	 * test verifies the correct effect of Sedatives itemCard
	 */
	@Test
	public void testSedatives() {
		status = new GameStatus(model, player);
		status.setSedated(false);

		new UseItem(status).sedatives();

		assertTrue(status.isSedated());
	}

	/**
	 * test verifies that players in the sector chosen by a human using
	 * Spotlight itemCard declare their position
	 */
	@Test
	public void testSpotlightChosen() {
		Coordinate coord = new Coordinate(12, 3);
		Sector chosen = model.getMap().getSector(coord);

		Player p1 = model.getPlayers(1);
		Player p2 = model.getPlayers(3);

		p1.setSector(chosen);
		chosen.addPlayer(p1);
		p2.setSector(chosen);
		chosen.addPlayer(p2);

		status = new GameStatus(model, player);

		UseItem ui = new UseItem(status);
		ui.spotlight(coord);

		assertEquals(ui.getDtoGame().getCoordinate()[1], coord);
		assertEquals(ui.getDtoGame().getCoordinate()[3], coord);
	}

	/**
	 * test verifies that players in adjacent sectors of Spotlight's chosen
	 * sector declare their position
	 */
	@Test
	public void testSpotlightAdjacent() {
		Coordinate coord = new Coordinate(12, 3);

		Player p1 = model.getPlayers(2);
		Player p2 = model.getPlayers(4);
		Player p3 = model.getPlayers(6);

		Coordinate adj1 = new Coordinate(12, 2);
		Sector s1 = model.getMap().getSector(adj1);

		Coordinate adj2 = new Coordinate(12, 4);
		Sector s2 = model.getMap().getSector(adj2);

		p1.setSector(s1);
		s1.addPlayer(p1);

		p2.setSector(s1);
		s1.addPlayer(p2);

		p3.setSector(s2);
		s2.addPlayer(p3);

		status = new GameStatus(model, player);

		UseItem ui = new UseItem(status);
		ui.spotlight(coord);

		assertEquals(ui.getDtoGame().getCoordinate()[2], adj1);
		assertEquals(ui.getDtoGame().getCoordinate()[4], adj1);
		assertEquals(ui.getDtoGame().getCoordinate()[6], adj2);
	}

	/**
	 * test verifies correct effect of Adrenaline itemCard
	 */
	@Test
	public void testAdrenaline() {
		player.setSpeed(1);
		status = new GameStatus(model, player);

		new UseItem(status).adrenaline();

		assertEquals(player.getSpeed(), 2);
	}

	/**
	 * test verifies that teleport is used when a human player wants
	 * 
	 */
	@Test
	public void testUseTeleport() {
		Coordinate coord = new Coordinate(6, 1);
		Sector current = model.getMap().getSector(coord);
		player.setSector(current);
		current.addPlayer(player);

		status = new GameStatus(model, player);
		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.TELEPORT, null);

		new UseItem(status).doAction(dtoTurn);

		Coordinate human = new Coordinate(12, 8);

		assertEquals(player.getSector().getCoordinate(), human);
	}

	/**
	 * test verifies that sedatives is used when a human player wants
	 */
	@Test
	public void testUseSedatives() {
		status = new GameStatus(model, player);
		status.setSedated(false);
		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.SEDATIVES, null);

		new UseItem(status).doAction(dtoTurn);

		assertTrue(status.isSedated());

	}

	/**
	 * test verifies that spotlight is used when a human player wants
	 */
	@Test
	public void testUseSpotlight() {
		Player p = model.getPlayers(2);
		Coordinate c = new Coordinate(12, 4);
		Sector s = model.getMap().getSector(c);
		p.setSector(s);
		s.addPlayer(p);

		status = new GameStatus(model, player);
		Coordinate coord = new Coordinate(12, 3);
		DTOTurn dtoTurn = new DTOTurn(coord, ItemCardType.SPOTLIGHT, null);

		DTOGame dtoGame = new UseItem(status).doAction(dtoTurn);

		assertEquals(dtoGame.getCoordinate()[2], c);
	}

	/**
	 * test verifies that adrenaline is used when a human player wants
	 */
	@Test
	public void testUseAdrenaline() {
		player.setSpeed(1);
		status = new GameStatus(model, player);

		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.ADRENALINE, null);

		new UseItem(status).doAction(dtoTurn);

		assertEquals(player.getSpeed(), 2);
	}

	/**
	 * test verifies that player use attack in the right moment of the turn
	 */
	@Test
	public void testUseAttack() {
		Coordinate c = new Coordinate(3,6);
		Sector s = model.getMap().getSector(c);
		player.setSector(s);
		s.addPlayer(player);
		
		status = new GameStatus(model, player);
		status.setAttacked(false);
		status.setMoved(true);

		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.ATTACK, null);

		new UseItem(status).doAction(dtoTurn);

		assertTrue(status.isAttacked());
	}

}
