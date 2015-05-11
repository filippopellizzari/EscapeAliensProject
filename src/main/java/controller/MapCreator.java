package controller;

import model.*;

public abstract class MapCreator {
	protected Game game;
	protected Map map;
	public abstract Map createMap();
}
