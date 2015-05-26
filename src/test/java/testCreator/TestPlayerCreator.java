package testCreator;

import static org.junit.Assert.*;
import model.*;
import creator.*;

import org.junit.Test;


public class TestPlayerCreator {

	@Test
	public void test() {
		
		
		MapCreator mc = new MapCreator();
		Map m = mc.createMap("Galilei", "Exagonal");
		PlayerCreator pc = new PlayerCreator(m);
		Player[] players = pc.createPlayer(6);
		assertEquals (players.length, 6);
		
		
	}

}
