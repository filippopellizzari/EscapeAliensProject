package testCreator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.Test;

public class TestCreatorGame {
	
	
	@Test
	public void testCreateGame() {
		Game game;
		GameCreator gameCreator=GameCreator.getinstance();
		game=gameCreator.createGame("FermiMap", 2, "Exagonal");
		assertTrue(game instanceof Game);
	}
}
