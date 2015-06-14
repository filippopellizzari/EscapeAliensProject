package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import controller.GameStatus;
import controller.SelectSectorNoise;
import creator.GameCreator;
import dto.DTOGame;
import dto.DTOTurn;
import model.*;
/**
 * this class tests that a player selects in the right moment
 * a coordinate to do "noise in any sector"
 * 
 * @author Filippo
 *
 */
public class TestSelectSectorNoise {

	GameStatus status;
	Game model;
	Player player;
	
	@Before
	public void always() throws NumberFormatException, IOException{
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
	}
	/**
	 * test verifies that a player who must select a coordinate 
	 * does this action in the right moment
	 */
	@Test
	public void testRightMoment() {
		status = new GameStatus(model,player);
		status.setMustNoise(true);
		
		Coordinate chosen = new Coordinate(5,2);
		DTOTurn dtoTurn = new DTOTurn(chosen, null, null);
		DTOGame dtoGame = new SelectSectorNoise(status).doAction(dtoTurn);
		
		assertEquals(dtoGame.getCoordinate()[0],chosen);
	}
	/**
	 * test verifies that a player who has not to choose a coordinate
	 * can not select it to do "noise"
	 */
	@Test
	public void testWrongMoment(){
		status = new GameStatus(model,player);
		status.setMustNoise(false);
		
		Coordinate chosen = new Coordinate(5,2);
		DTOTurn dtoTurn = new DTOTurn(chosen, null, null);
		DTOGame dtoGame = new SelectSectorNoise(status).doAction(dtoTurn);
		
		assertEquals(dtoGame.getCoordinate()[0],null);
	}

}
