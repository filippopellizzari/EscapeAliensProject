package controller;

import model.*;

public abstract class MapCreator {
	protected Map map;
	LoadExagonalMap loadExagonalmap=new LoadExagonalMap();
	public abstract Map createMap();
}
