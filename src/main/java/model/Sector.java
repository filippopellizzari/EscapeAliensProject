package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Sector extends Coordinate{
	protected TypeSector typeSector;
	protected boolean crossable;
	Queue<Player> playerQueue=new LinkedList<Player>();
	List<Coordinate> coordinate;
	public Sector (TypeSector typeSector, boolean crossable, int x, int y, List<Coordinate> nextSector)
	{
		super(x,y);
		this.typeSector=typeSector;
		this.crossable=crossable;
		coordinate=nextSector;
	}
	public boolean isCrossable(){
		return crossable;
	}
	public void addPlayer(Player player){
		playerQueue.add(player);
	}
	public Player removePlayer() {
		return playerQueue.remove();
	}
}
