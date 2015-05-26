package controller;

import java.io.IOException;

import creator.GameCreator;
import model.*;

/**
 * 
 * @author Filippo
 *
 */

public class GameController {
	private Game game;
	private Turn currentTurn;
	private int turnNumber;
	private int currentPlayer;
	
	public GameController(String mapName, int numberOfPlayers, String typeMap) throws NumberFormatException, IOException {
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, typeMap);
		currentTurn = new Turn(game,game.getPlayers(currentPlayer));
		this.currentPlayer=0;
		this.turnNumber=1;
	}
	public String doAnAction(DTOSend dtoSend) {
		String message="";
		ControlDataRiceived control=new ControlDataRiceived();			//controlla validità dati passati
		if(control.verify(dtoSend,currentPlayer, game)=="OK") {								//se la risposta è ok fa la verifica del turno
			DTOTurn dtoTurn=new DTOTurn(dtoSend.getCoordinate(),dtoSend.getTypeCard(),dtoSend.getTypeOfAction());
			message=currentTurn.turn(dtoTurn);							//messaggio di come è stata eseguita l'azione
			if(message=="Hai finito il turno") endTurn();
		}
		return message;
	}
	
	public void endTurn() {			//aggiorna il giocatore
		 {
			ControlEndGame controlEndGame=new ControlEndGame(game,turnNumber);		//controlla la fine della partita, se si la elimina e avvisa i giocatori
			turnNumber++;		//turno finito
			boolean nextPlayerDecide=false;			//serve per assegnare correttamente il prossimo giocatore (ci possono essere cadaveri da saltare)
			do{
				currentPlayer++;
				if(game.getPlayers().length==currentPlayer)
					currentPlayer=0;		//giocatore a cui tocca
				if(game.getPlayers(currentPlayer).isAlive()==true) 
					nextPlayerDecide=true;
			} while(nextPlayerDecide==false);
			currentTurn=new Turn(game,game.getPlayers(currentPlayer));
		}
	}
	
	public void finishTurn() {
		CompleteTurn completeTurn=new CompleteTurn(currentTurn.getGameStatus());
		String message=completeTurn.completeTurn();									//completa il turno
		endTurn();																	//crea il prossimo turno
	}
	
	public static void main(String[] args) {
	}
}
