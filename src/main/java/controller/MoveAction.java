package controller;
import model.*;

public class MoveAction {

	
	private Game model;
	private Player player;

	
	public MoveAction(Game model, Player player){
		this.model = model;
		this.player = player;
	}
	
	public void Move(Coordinate destCoord){
			Sector destSector = model.getMap().getSector(destCoord);
			destSector.addPlayer(player.getSector().removePlayer());
			player.setSector(destSector);
			
	}
	
	
	
	
	
}
