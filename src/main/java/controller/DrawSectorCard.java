package controller;

import dto.*;
import model.*;


public class DrawSectorCard implements TryToDoAnAction{
	
	private GameStatus gameStatus;
	private DTOGame dtoGame;
	
	public DrawSectorCard(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
		this.dtoGame=new DTOGame();
	}

	public void drawSectorCard(){
		SectorCard current = gameStatus.getGame().getSectorCards().draw();
		SectorCardType type = current.getType();
		dtoGame.setSectorType(type);
		switch(type){
			case NOISEANY: 
			dtoGame.setGameMessage("NOISE IN ANY SECTOR: scegli una coordinata\n"); //messaggio privato
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
			break;
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
		if (current.isItemIcon()){
			drawItemCard();
		}
		gameStatus.getGame().getSectorCards().discard(current);
	}
	
	private void drawItemCard(){
		ItemCard current = gameStatus.getGame().getItemCards().draw();
		if(current == null){
			dtoGame.setGameMessage("Sono finite le carte oggetto da pescare!\n");
		}
		else {
			gameStatus.getPlayerPlay().addItem(current);
			ItemCardType type = current.getType();
			dtoGame.setTypeItemCard(type);
			if(gameStatus.getPlayerPlay().getItem().size() == 4){
				dtoGame.setGameMessage("Hai 4 carte oggetto: devi giocarne una subito o scartarne una\n"); 
				gameStatus.setDiscardItemDuty(true);
			}
		}
	}
	

	@Override
	public DTOGame doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()&&gameStatus.isAttack()==false&&gameStatus.isNoiseInAnySector()&&gameStatus.isSolveSectorDuty()==false){   //pesca carta settore pericoloso
			gameStatus.setSolveSectorDuty(true);
			drawSectorCard();
			if(gameStatus.getPlayerPlay().getItem().size()==4) 
				gameStatus.setDiscardItemDuty(true);
		}
		else {
			dtoGame.setDestination(gameStatus.getPlayerPlay().getNumber());
			dtoGame.setGameMessage("Non puoi pescare in questo momento");
		}
		return dtoGame;
	}

}
