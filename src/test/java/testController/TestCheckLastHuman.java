package testController;

import static org.junit.Assert.*;

import java.io.IOException;

import model.Game;

import org.junit.Before;
import org.junit.Test;

import connection.MapName;
import connection.MapType;
import creator.GameCreator;
import model.*;

public class TestCheckLastHuman {

	Game model;
	Player player;
	
	@Before
	public void always() throws NumberFormatException, IOException {
		model = GameCreator.getinstance().createGame(MapName.Galilei, 8,
				MapType.HEXAGONAL);
		player = model.getPlayers(0);
	}


}
