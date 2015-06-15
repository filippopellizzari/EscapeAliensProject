package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.ActionType;
import controller.DrawSectorCard;
import controller.GameStatus;
import creator.GameCreator;
import dto.DTOTurn;
import model.*;

/**
 * this class tests what happens when a player tries to draw a dangerous sector
 * card and, consequently, an item card
 * 
 * 
 * 
 * @author Filippo
 *
 */
public class TestDrawSectorCard {

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
	 * test verifies that a player draw correctly an itemCard
	 */
	@Test
	public void testDrawItemCard() {
		model.getItemCards().getDeck().clear();

		ItemCard i1 = new ItemCard(ItemCardType.ATTACK);
		ItemCard i2 = new ItemCard(ItemCardType.ADRENALINE);

		model.getItemCards().getDeck().add(i1);
		model.getItemCards().getDeck().add(i2);

		status = new GameStatus(model, player);
		new DrawSectorCard(status).drawItemCard();
		new DrawSectorCard(status).drawItemCard();

		assertEquals(player.getItem().get(1).getType(), ItemCardType.ADRENALINE);
	}

	/**
	 * test verifies that a player, when draws the fourth itemCard, is obliged
	 * to discard or use one of his itemCards
	 */
	@Test
	public void testFouthItemCard() {
		ItemCard i1 = new ItemCard(ItemCardType.ATTACK);
		ItemCard i2 = new ItemCard(ItemCardType.ADRENALINE);
		ItemCard i3 = new ItemCard(ItemCardType.ADRENALINE);

		player.addItem(i1);
		player.addItem(i2);
		player.addItem(i3);

		status = new GameStatus(model, player);
		status.setMustDiscardItem(false);

		new DrawSectorCard(status).drawItemCard();

		assertTrue(status.isMustDiscardItem());
	}

	/**
	 * test verifies that when there are no itemCards in the deck and in the
	 * discard pile (because all cards are in possession of players) a player is
	 * obliged to pass
	 */
	@Test
	public void testEmptyDeckItem() {
		model.getItemCards().getDeck().clear();
		model.getItemCards().getDiscardPile().clear();

		status = new GameStatus(model, player);

		new DrawSectorCard(status).drawItemCard();

		assertEquals(player.getItem().size(), 0);
	}

	/**
	 * test verifies that a player discard correctly a dangerous sector card
	 */
	@Test
	public void testDiscardSectorCard() {
		model.getSectorCards().getDeck().clear();
		model.getSectorCards().getDiscardPile().clear();

		SectorCard s1 = new SectorCard(false, SectorCardType.NOISEANY);
		SectorCard s2 = new SectorCard(false, SectorCardType.SILENCE);

		model.getSectorCards().getDeck().add(s1);
		model.getSectorCards().getDeck().add(s2);

		status = new GameStatus(model, player);
		new DrawSectorCard(status).drawSectorCard();
		new DrawSectorCard(status).drawSectorCard();

		assertEquals(model.getSectorCards().getDiscardPile().get(1).getType(),
				SectorCardType.SILENCE);

	}

	/**
	 * test verifies that a player is obliged to select a sector after drawing a
	 * "noise in any sector" card;
	 */
	@Test
	public void testNoiseAnyCard() {
		model.getSectorCards().getDeck().clear();
		model.getSectorCards().getDiscardPile().clear();

		SectorCard s1 = new SectorCard(false, SectorCardType.NOISEANY);
		SectorCard s2 = new SectorCard(false, SectorCardType.SILENCE);

		model.getSectorCards().getDeck().add(s1);
		model.getSectorCards().getDeck().add(s2);

		status = new GameStatus(model, player);
		status.setMustNoise(false);
		new DrawSectorCard(status).drawSectorCard();

		assertTrue(status.isMustNoise());
	}
	/**
	 * test verifies that when a player draws a "noise in your sector" card
	 * declares his position (set his coordinates in dtoGame)
	 */
	@Test
	public void testNoiseYourCard(){
		model.getSectorCards().getDeck().clear();
		model.getSectorCards().getDiscardPile().clear();

		SectorCard s = new SectorCard(false, SectorCardType.NOISEYOUR);
		model.getSectorCards().getDeck().add(s);
		
		Coordinate coord = new Coordinate(3,3);
		Sector current = model.getMap().getSector(coord);
		player.setSector(current);
		current.addPlayer(player);
		
		status = new GameStatus(model, player);
		DrawSectorCard d = new DrawSectorCard(status);
		d.drawSectorCard();
		
		assertEquals(d.getDtoGame().getCoordinate()[0],coord);
	}
	/**
	 * test verifies that when there is an item icon on the sector card drawn
	 * player must draw an itemCard
	 */
	@Test
	public void testSilenceAndDrawItem(){
		model.getSectorCards().getDeck().clear();
		model.getSectorCards().getDiscardPile().clear();
		model.getItemCards().getDeck().clear();
		model.getItemCards().getDiscardPile().clear();

		SectorCard s = new SectorCard(true, SectorCardType.SILENCE);
		ItemCard i = new ItemCard(ItemCardType.ATTACK);
		
		model.getSectorCards().getDeck().add(s);
		model.getItemCards().getDeck().add(i);
		
		status = new GameStatus(model, player);
		new DrawSectorCard(status).drawSectorCard();
		
		assertEquals(player.getItem().get(0).getType(), ItemCardType.ATTACK);
	}
	
	/**
	 * test verifies that when there is not an item icon on the sector card drawn
	 * player can not draw an itemCard
	 */
	@Test
	public void testSilenceAndNotDrawItem(){
		model.getSectorCards().getDeck().clear();
		model.getSectorCards().getDiscardPile().clear();
		model.getItemCards().getDeck().clear();
		model.getItemCards().getDiscardPile().clear();

		SectorCard s = new SectorCard(false, SectorCardType.SILENCE);
		ItemCard i = new ItemCard(ItemCardType.ATTACK);
		
		model.getSectorCards().getDeck().add(s);
		model.getItemCards().getDeck().add(i);
		
		status = new GameStatus(model, player);
		new DrawSectorCard(status).drawSectorCard();
		
		assertEquals(player.getItem().size(),0);
	}
	/**
	 * test verifies that a player not obliged to draw a sector card
	 * can not draw it
	 */
	@Test
	public void testNotMustDraw(){
		status = new GameStatus(model, player);
		status.setMustDraw(false);
		status.setAttacked(false);
		
		DTOTurn dtoTurn = new DTOTurn(null, null, ActionType.DRAWSECTORCARD);
		new DrawSectorCard(status).doAction(dtoTurn);
		
		assertFalse(status.isAttacked());	
	}
	
	/**
	 * test verifies that a player obliged to draw a sector card
	 * can draw it
	 */
	@Test
	public void testMustDraw(){
		status = new GameStatus(model, player);
		status.setMustDraw(true);
		
		DTOTurn dtoTurn = new DTOTurn(null, null, ActionType.DRAWSECTORCARD);
		new DrawSectorCard(status).doAction(dtoTurn);
		
		assertFalse(status.isMustDraw());	
	}
	

}
