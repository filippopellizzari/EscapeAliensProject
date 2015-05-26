package controller;

public class EndTurn implements TryToDoAnAction {

	private GameStatus gameStatus;

	public EndTurn(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}
	
	@Override
	public String doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()&&gameStatus.isNoiseInAnySector()==false&&
				gameStatus.isDiscardItemDuty()==false&&gameStatus.isSolveSectorDuty()) {	//fine turno
			return "Hai finito il turno";	
		}
		return "Non hai completato tutte le azioni obbligatorie per finire il turno";
	}

}
