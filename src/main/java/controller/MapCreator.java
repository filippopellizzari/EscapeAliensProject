package controller;

import model.Game;
import model.Map;

public abstract class MapCreator {
	protected Game game;
	protected Map map;
	public abstract Map createMap();
}
