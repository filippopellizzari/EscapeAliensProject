package controller;

import dto.DTOGame;
import dto.DTOTurn;
import model.*;

/**
 * This class contains the control of the move and the effective action of move
 * 
 * @author Filippo
 *
 */
public class Move implements ChooseAnAction {

	private GameStatus status;
	private DTOGame dtoGame;

	/**
	 * 
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */
	
	public Move(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}

	/**
	 * check the validity of move
	 * 
	 * @param dest
	 * @return
	 */

	public boolean moveCheck(Coordinate dest) {

		Player player = status.getPlayer();
		Sector destSector = status.getGame().getMap().getSector(dest);
		
		return pathCheck(player.getSector().getCoordinate(), dest, player.getSpeed())
			&& destCheck(player, destSector);
	}

	/**
	 * 
	 * check that an alien can not move to an escape hatch sector
	 * 
	 * @param dest
	 * @return
	 */

	private boolean destCheck(Player player, Sector destSector) {
		return  !(player.getType().equals(PlayerType.ALIEN) 
				&& destSector.getType().equals(SectorType.HATCH));
	}

	/**
	 * 
	 * Check that the destination is reached according to the speed player and
	 * that the crossed sectors are valid; it's a classic algorithm of Depth Search
	 * 
	 * @param curr, starting coordinate
	 * @param dest, destination coordinate
	 * @param speed, speed of player
	 * @return
	 */

	private boolean pathCheck(Coordinate curr, Coordinate dest, int speed) {
		if (speed == 0) {
			return curr.equals(dest);
		} else {
			Sector currSector = status.getGame().getMap().getSector(curr);
			for (int i = 0; i < currSector.getAdjacent().size(); i++) {
				Coordinate adjCoord = currSector.getAdjacent().get(i);
				if (adjCoord.getX() != -1) {
					Sector adjSector = status.getGame().getMap().getSector(adjCoord);
					if (!adjSector.isClosed()) {
						speed--;
						if (pathCheck(adjCoord, dest, speed)) {
							return true;
						}
						speed++;
					}

				}
			}
		}
		return false;
	}

	/**
	 * this is the effective move
	 * 
	 * 
	 * @param destCoord, coordinates chosen by player
	 * @return string, describes destination sector
	 */


	public void move(Coordinate destCoord) {
		Player player = status.getPlayer();
		Sector destSector = status.getGame().getMap().getSector(destCoord);
		destSector.addPlayer(player.getSector().removePlayer());
		player.setSector(destSector);
		if(player.getType().equals(PlayerType.HUMAN)){ //nel caso avesse preso adrenalina
			player.setSpeed(1);
		}
		dtoGame.setCoordinate(destCoord, player.getNumber()); //mossa avvenuta con successo
		switch (destSector.getType()) {
		case DANGEROUS: 
			if(!status.isSedated()){     
				status.setMustDraw(true); //ha l'obbligo di pescare(fino a che non decide di attaccare o pescare)
				//se è già stato sedato, non ha obbligo di pescare
			}
			dtoGame.setReceiver(status.getPlayer().getNumber());
		case SECURE:
			dtoGame.setReceiver(status.getPlayer().getNumber());
			break;
		case HATCH:
			drawHatchCard();
			dtoGame.setReceiver(9);	//mostra a tutti carta hatch e posizione giocatore
			break;
		default:
			break;
		}
	}
	
	/**
	 * If player is on a HatchSector, a HatchCard is draw and solved, everyone is advised by the result
	 */
	
	public void drawHatchCard(){
		HatchCard current = status.getGame().getHatchCards().draw();//pesco carta hatch
		HatchCardColor color = current.getColor(); 
		dtoGame.setHatchCardColor(color);
		switch(color){
		  	case RED :	
		  		break;
		  	case GREEN :
		  		status.getPlayer().setAlive(false);//partita conclusa per lui
		  		break;
		  	default:
		  		break;
		}
		status.getGame().getHatchCards().discard(current);
		
	}

	
	
	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if (!status.isMoved()) { 	//controllo stato del turno
			if (moveCheck(dtoTurn.getCoordinate())) {//controllo se la mossa è valida
				move(dtoTurn.getCoordinate());
				status.setMoved(true);
			} 
			else {
				dtoGame.setGameMessage("Non puoi muovere in quel settore");
				dtoGame.setReceiver(status.getPlayer().getNumber()); //notifica privata
			}
		} 
		else {
			dtoGame.setGameMessage("Non puoi muovere adesso");
			dtoGame.setReceiver(status.getPlayer().getNumber());  //notifica privata
		}
		
		return dtoGame;
	}
	
	

}
