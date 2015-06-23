package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import model.Coordinate;
import model.Game;
import model.Player;
import model.PlayerState;
import model.PlayerType;
import model.Sector;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.EndGame;
import creator.GameCreator;
/**
 * this class tests if game ends correctly, when players have not completed
 * all 39 rounds
 * 
 * 
 * @author Filippo
 *
 */
public class TestEndGame {

	Game model;
	Player alien1;
	Player alien2;
	Player human1;
	Player human2;
	Player human3;
	Player human4;
	
	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 6,
				MapType.HEXAGONAL);
		
		alien1 = model.getPlayers(0);
		alien1.setPlayerType(PlayerType.ALIEN);
		
		alien2 = model.getPlayers(2);
		alien2.setPlayerType(PlayerType.ALIEN);
		
		human1 = model.getPlayers(1);
		human1.setPlayerType(PlayerType.HUMAN);
		
		human2 = model.getPlayers(3);
		human2.setPlayerType(PlayerType.HUMAN);
		
		human3 = model.getPlayers(4);
		human3.setPlayerType(PlayerType.HUMAN);
		
		human4 = model.getPlayers(5);
		human4.setPlayerType(PlayerType.HUMAN);
		
	}
	
	/**
	 * test verifies correct end communication if last human is killed
	 * by an alien
	 */
	@Test
	public void testLastHumanKilled(){
		alien2.setPlayerState(PlayerState.WINNER);
	
		String s = new EndGame(model).control();
		
		assertEquals(s,"L'ultimo umano in gioco è stato ucciso: gli alieni vincono\n");
	}
	/**
	 * test verifies correct end communication if all humans are winner
	 */
	@Test
	public void testAllHumansWin(){
		human1.setPlayerState(PlayerState.WINNER);
		human2.setPlayerState(PlayerState.WINNER);
		human3.setPlayerState(PlayerState.WINNER);
		human4.setPlayerState(PlayerState.WINNER);
		
		String s = new EndGame(model).control();
		
		assertEquals(s, "Tutti gli umani sono riusciti a scappare: gli umani vincono\n");
	}
	/**
	 * test verifies  correct end communication if all hatchSectors are closed
	 * but not all humans have escaped;
	 */
	@Test
	public void testAllHatchClosed(){
		Coordinate h1 = new Coordinate(2,2);
		Coordinate h2 = new Coordinate(2,13);
		Coordinate h3 = new Coordinate(22,2);
		Coordinate h4 = new Coordinate(22,13);
		
		Sector s1 = model.getMap().getSector(h1);
		Sector s2 = model.getMap().getSector(h2);
		Sector s3 = model.getMap().getSector(h3);
		Sector s4 = model.getMap().getSector(h4);
		
		human1.setSector(s1);
		s1.addPlayer(human1);
		human2.setSector(s2);
		s2.addPlayer(human2);
		human3.setSector(s3);
		s3.addPlayer(human3);
		human4.setSector(s4);
		s4.addPlayer(human4);
		
		human1.setPlayerState(PlayerState.WINNER);
		human2.setPlayerState(PlayerState.WINNER);
		human3.setPlayerState(PlayerState.WINNER);
		
		String s = new EndGame(model).control();
		
		assertEquals(s, "Non tutti gli umani sono riusciti a scappare: la partita finisce\n");
	}
	/**
	 * test verifies that game is not finished if some humans win and the human killed is not 
	 * the last human in game.
	 */
	@Test
	public void testNotFinished(){
		Coordinate h1 = new Coordinate(2,2);
		Coordinate h2 = new Coordinate(2,13);
		
		Sector s1 = model.getMap().getSector(h1);
		Sector s2 = model.getMap().getSector(h2);
	
		
		human1.setSector(s1);
		s1.addPlayer(human1);
		human2.setSector(s2);
		s2.addPlayer(human2);
	
		human1.setPlayerState(PlayerState.WINNER);
		human2.setPlayerState(PlayerState.WINNER);
		human3.setPlayerState(PlayerState.KILLED);
		human4.setPlayerState(PlayerState.PLAYING);
		
		alien1.setPlayerState(PlayerState.KILLED);
		alien2.setPlayerState(PlayerState.PLAYING);
		
		String s = new EndGame(model).control();
		
		assertEquals(s, null);
	}
	
	

}
