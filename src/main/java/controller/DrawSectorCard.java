package controller;

import dto.*;
import model.*;

/**
 * This class is used when a player is in a dangerous sector and wants to draw a card
 * @author Nicola
 *
 */

public class DrawSectorCard implements ChooseAnAction{
	
	private GameStatus status;
	private DTOGame dtoGame;
	
	/**
	 * 
	 * @param gameStatus, the status of a turn, reference at model and the player who
	 * are playing, now is his turn
	 */
	
	public DrawSectorCard(GameStatus status) {
		this.status = status;
		this.dtoGame = new DTOGame();
	}
	
	/**
	 * Draw a dangerous sector card and solve the effect immediately if possible
	 */

	public void drawSectorCard(){
		SectorCard current = status.getGame().getSectorCards().draw();
		SectorCardType type = current.getType();
		dtoGame.setSectorCardType(type);
		switch(type){
			case NOISEANY: 
			dtoGame.setGameMessage("NOISE IN ANY SECTOR: scegli una coordinata\n"); //messaggio privato
			dtoGame.setReceiver(10);		//unica volta che il messaggio è privato ma parte di esso va messo nel buffer per essere poi
			break;	//dato a tutti i giocatori
			case NOISEYOUR:  
			dtoGame.setReceiver(9);
			dtoGame.setCoordinate(status.getPlayer().getSector().getCoordinate(),
					status.getPlayer().getNumber());
			break;
			case SILENCE:
			dtoGame.setReceiver(9);
			break;
			default:
			break;		
		}
		if (current.isItemIcon()){		//vedi se devi pescare la carta
			drawItemCard();
		}
		status.getGame().getSectorCards().discard(current);	//scarta la carta settore nel mazzo scarti
	}
	

	public void drawItemCard(){
		ItemCard current = status.getGame().getItemCards().draw();
		if(current == null){
			dtoGame.setGameMessage("Sono finite le carte oggetto da pescare!\n");
		}
		else {
			status.getPlayer().addItem(current);//aggiungi la carta oggetto nel mazzo privato del giocatore
			ItemCardType type = current.getType();
			dtoGame.setItemCardType(type);			//il giocatore deve conoscere 
			if(status.getPlayer().getItem().size() == 4){
				dtoGame.setGameMessage("Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"); 
				status.setMustDiscardItem(true); //è obbligato a scartarne una (fino a che non la scarta o la usa)
			}
		}
	}
	

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if(status.isMustDraw() && !status.isSedated()){   
			drawSectorCard();
			status.setMustDraw(false);
		}
		else {
			dtoGame.setGameMessage("Non puoi pescare in questo momento");
			dtoGame.setReceiver(status.getPlayer().getNumber());
		}
		return dtoGame;
	}

}
