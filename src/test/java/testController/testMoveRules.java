package testController;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import creator.*;
import model.*;
import controller.*;

public class testMoveRules {

	@Test
	public void testDistanceCheck() {
		//TODO
		Game model = GameCreator.getinstance().createGame("Galilei", 8, "Exagonal");
	
		Player player = model.getPlayers(0);
		Coordinate start = new Coordinate(12,5);
		Sector s = model.getMap().getSector(start);
		player.setCurrentSector(s);
		
		MoveRules moveRules = new MoveRules(model, player);
		
		
		Coordinate dest = new Coordinate (12,3);
	
		assertTrue(moveRules.distanceCheck(start,dest,2));
		
		
	}

}
