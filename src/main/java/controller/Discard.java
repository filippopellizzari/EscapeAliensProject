package controller;

import model.ItemCardType;

public class Discard implements TryToDoAnAction {

	private GameStatus gameStatus;

	public Discard(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()&&gameStatus.isDiscardItemDuty()&&dtoTurn.getTypeCard()!=null){	//scarta carta
			discard(dtoTurn.getTypeCard());
			return "Carta scartata";
		}
		else
			return "Non puoi scartare questa carta adesso";
	}
	
	private void discard(ItemCardType type){
		for(int i = 0; i < gameStatus.getPlayerPlay().getItem().size(); i++){
			if(gameStatus.getPlayerPlay().getItem().get(i).getType().equals(type)){
				gameStatus.getGame().getItemCards().discard(gameStatus.getPlayerPlay().removeItem(i));
			}
		}
	}

}
