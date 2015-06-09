package controller;

import dto.*;
import model.*;

/**
 * This class is used when a player is in a dangerous sector and wants to draw a card
 * @author Nicola
 *
 */

public class DrawSectorCard implements TryToDoAnAction{
	
	private GameStatus gameStatus;
	private DTOGame dtoGame;
	
	/**
	 * 
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */
	
	public DrawSectorCard(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
		this.dtoGame=new DTOGame();
	}
	
	/**
	 * Draw a dangerous sector card and solve the effect immediatly if possible
	 */

	public void drawSectorCard(){
		SectorCard current = gameStatus.getGame().getSectorCards().draw();
		SectorCardType type = current.getType();
		dtoGame.setSectorType(type);
		switch(type){
			case NOISEANY: 
			dtoGame.setGameMessage("NOISE IN ANY SECTOR: scegli una coordinata\n"); //messaggio privato
			dtoGame.setDestination(10);		//unica volta che il messaggio è privato ma parte di esso va messo nel buffer per essere poi
			break;							//dato a tutti i giocatori
			case NOISEYOUR:  
			dtoGame.setDestination(9);
			dtoGame.setCoordinate(gameStatus.getPlayerPlay().getSector().getCoordinate(),
					gameStatus.getPlayerPlay().getNumber());
			break;
			case SILENCE:
			dtoGame.setDestination(9);
			break;
			default:
			break;		
		}
		if (current.isItemIcon()){		//vedi se devi pescare la carta
			drawItemCard();
		}
		gameStatus.getGame().getSectorCards().discard(current);			//scarta la carta settore nel mazzo scarti
	}
	
	/**
	 * If with the sector card there is a object card add this if possible at player's hand 
	 */
	
	private void drawItemCard(){
		ItemCard current = gameStatus.getGame().getItemCards().draw();
		if(current == null){
			dtoGame.setGameMessage("Sono finite le carte oggetto da pescare!\n");
		}
		else {
			gameStatus.getPlayerPlay().addItem(current);		//aggiungi la carta
			ItemCardType type = current.getType();
			dtoGame.setTypeItemCard(type);						//setta la carta da passare al giocatore così che sappia cosa ha pescato
			if(gameStatus.getPlayerPlay().getItem().size() == 4){
				dtoGame.setGameMessage("Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"); //messaggio privato
				gameStatus.setDiscardItemDuty(true);
			}
		}
	}
	

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()&&gameStatus.isAttack()==false&&gameStatus.isNoiseInAnySector()&&gameStatus.isSolveSectorDuty()==false){   //pesca carta settore pericoloso
			gameStatus.setSolveSectorDuty(true);
			drawSectorCard();
			if(gameStatus.getPlayerPlay().getItem().size()==4) //dovrà scartare
				gameStatus.setDiscardItemDuty(true);
		}
		else {
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
			dtoGame.setGameMessage("Non puoi pescare in questo momento");
		}
		return dtoGame;
	}

}
