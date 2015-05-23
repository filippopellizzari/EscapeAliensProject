package controller;
import model.*;

public class MoveAction {

	
	private Game model;
	private Player player;
	private MoveRules moveRules;
	
	public MoveAction(Game model, Player player){
		this.model = model;
		this.player = player;
		this.moveRules = new MoveRules(model,player);
		
	}
	
	public void Move(Coordinate destCoord){
			Sector destSector = model.getMap().getSector(destCoord);
			destSector.addPlayer(player.getCurrentSector().removePlayer());
			player.setCurrentSector(destSector);	
	}
	
	
	
	
	
}
