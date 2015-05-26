package testCreator;

import static org.junit.Assert.*;

import java.io.IOException;

import model.*;
import creator.*;

import org.junit.Test;


public class TestPlayerCreator {

	@Test
	public void test() throws NumberFormatException, IOException {
		
		
		MapCreator mc = new LoadHexagonalMap();
		Map m = mc.loadMap("Galilei");
		PlayerCreator pc = new PlayerCreator(m);
		Player[] players = pc.createPlayer(6);
		assertEquals (players.length, 6);
		
		
	}

}
