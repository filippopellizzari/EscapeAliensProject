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
import dto.DTOTurn;

public class TestDiscardItem {

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
	 * test verifies that it is discarded only an adrenaline card
	 */
	@Test
	public void testDiscardOnlyOneItem() {
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ATTACK));

		status = new GameStatus(model, player);

		new DiscardItem(status).discardItem(ItemCardType.ADRENALINE);

		assertEquals(player.getItem().size(), 2);
	}

	/**
	 * test verifies that is not discarded a card which is not in the player's
	 * deck
	 */
	@Test
	public void testDiscardAnything() {
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ATTACK));

		status = new GameStatus(model, player);

		new DiscardItem(status).discardItem(ItemCardType.SEDATIVES);

		assertEquals(player.getItem().size(), 2);
	}

	/**
	 * test verifies that a player can choose an action "discard item", if he
	 * has drawn the fourth itemCard but he has not used it immediately (he is
	 * obliged to discard, but he can choose when); in this case, the player
	 * decides to discard AdrenalineCard;
	 */
	@Test
	public void testMustDiscard() {
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ATTACK));

		status = new GameStatus(model, player);
		status.setMustDiscardItem(true);

		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.ADRENALINE, null);
		new DiscardItem(status).doAction(dtoTurn);

		assertEquals(player.getItem().size(), 1);
	}
	
	/**
	 * test verifies that a player, who is not obliged to discard an itemCard,
	 * can not discard
	 */
	@Test
	public void testCannotDiscard() {
		player.addItem(new ItemCard(ItemCardType.ADRENALINE));
		player.addItem(new ItemCard(ItemCardType.ATTACK));

		status = new GameStatus(model, player);
		status.setMustDiscardItem(false);

		DTOTurn dtoTurn = new DTOTurn(null, ItemCardType.ADRENALINE, null);
		new DiscardItem(status).doAction(dtoTurn);

		assertEquals(player.getItem().size(), 2);
	}

}
