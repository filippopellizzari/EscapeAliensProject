package testController;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import creator.*;
import model.*;
import controller.*;

/**
 * Test sulla validazione di una mossa dei giocatori (spostamento sulla mappa "Galilei")
 * @author Filippo
 *
 */
public class TestMove {

	
	static Game model;
	static Player human;
	static Player alien;
	static Player alienFed; //alieno che è riuscito ad eliminare almeno un umano
	
	
	@BeforeClass public static void onlyOnce() {
		model = GameCreator.getinstance().createGame("Galilei", 8, "Exagonal");	
		
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
	 * il test verifica che un umano non può spostarsi di due settori (non sta usando l'adrenalina)
	 */
	@Test
	public void testHumanMove() {
		GameStatus status = new GameStatus(model, human);
		Coordinate curr = new Coordinate(12,5);
		Sector s = model.getMap().getSector(curr);
		human.setSector(s);
		
		Coordinate dest = new Coordinate (12,3);
		assertFalse(new Move(status).moveCheck(dest));
	}
	
	/**
	 * il test verifica che un alieno può spostarsi di due settori (non ha ancora eliminato nessuno)
	 */
	@Test
	public void testAlienMove() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(12,5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = new Coordinate (12,3);
		assertTrue(new Move(status).moveCheck(dest));
	}
	
	/**
	 * il test verifica che un alieno non può spostarsi su un settore scialuppa
	 */
	@Test
	public void testAlienMoveToHatch() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(4,3);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = new Coordinate (2,2);
		assertFalse(new Move(status).moveCheck(dest));
		
	}
	
	/**
	 * il test verifica che un alieno "nutrito" può spostarsi di 3 settori
	 */
	@Test
	public void testAlienFedMove() {
		GameStatus status = new GameStatus(model, alienFed);
		Coordinate curr = new Coordinate(12,5);
		Sector s = model.getMap().getSector(curr);
		alienFed.setSector(s);
		
		Coordinate dest = new Coordinate (13,6);
		assertTrue(new Move(status).moveCheck(dest));
	}
	
	/**
	 * il test verifica che un generico giocatore (ad es. alieno) non possa raggiungere 
	 * un settore non presente sulla mappa (anche se rispetta la distanza)
	 */
	@Test 
	public void testMoveToNull() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(6,5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = new Coordinate (4,4);
		assertFalse(new Move(status).moveCheck(dest));	
	}
	
	/**
	 *il test verifica che un generico giocatore (ad es. alieno) non possa raggiungere 
	 *un settore della mappa attraversando settori "nulli" (anche se rispetta la distanza)
	 */
	@Test 
	public void testMoveThroughNull() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(5,5);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = new Coordinate (3,4);
		assertFalse(new Move(status).moveCheck(dest));	
	}
	
	/**
	 * il test verifica che un generico giocatore(ad es. alieno) non possa 
	 * raggiungere un settore "chiuso"(es. settore umano di partenza),
	 * anche se rispetta la distanza
	 */
	@Test
	public void testMoveToClosedSector() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(14,9);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = model.getMap().getHumanCoord();
		assertFalse(new Move(status).moveCheck(dest));
	}
	

	/**
	 *il test verifica che un generico giocatore (ad es. alieno) non possa raggiungere
	 *un settore della mappa attraversando un settore "chiuso" (es. settore umano di partenza),
	 *anche se rispetta la distanza 
	 */
	@Test
	public void testMoveThroughClosedSector() {
		GameStatus status = new GameStatus(model, alien);
		Coordinate curr = new Coordinate(13,8);
		Sector s = model.getMap().getSector(curr);
		alien.setSector(s);
		
		Coordinate dest = new Coordinate (11,9);
		assertFalse(new Move(status).moveCheck(dest));
	}
	
	
	
	
	
	

}
