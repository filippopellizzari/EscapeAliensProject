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
	
	public void Move(Coordinate dest){
		//TODO
		if (moveRules.moveCheck(dest)){
			Sector d = model.getMap().getSector(dest);
			d.addPlayer(player.getCurrentSector().removePlayer());
			player.setCurrentSector(d);
			
		}
		
	}
	
	
	
	
	
}
