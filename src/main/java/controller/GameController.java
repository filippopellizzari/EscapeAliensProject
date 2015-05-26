package controller;

import creator.GameCreator;
import model.*;

/**
 * 
 * @author Filippo
 *
 */

public class GameController {
	private static int numberOfGames = 0;
	private final int numberOfThisGame;
	private Game game;
	private Turn currentTurn;
	private int turnNumber;
	private int currentPlayer;
	
	public GameController(String mapName, int numberOfPlayers, String typeMap) {
		numberOfGames++;
		this.numberOfThisGame=numberOfGames;
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, typeMap);
		currentTurn = new Turn(game,game.getPlayers(currentPlayer));
		this.currentPlayer=0;
		this.turnNumber=1;
	}
	
	/**
	 * @return the numberOfThisGame
	 */
	
	public int getNumberOfThisGame() {
		return numberOfThisGame;
	}
	public String doAnAction(DTOSend dtoSend) {
		String message="";
		ControlDataRiceived control=new ControlDataRiceived();			//controlla validità dati passati
		if(control.verify(dtoSend,currentPlayer, game)=="OK") {								//se la risposta è ok fa la verifica del turno
			DTOTurn dtoTurn=new DTOTurn(dtoSend.getCoordinate(),dtoSend.getTypeCard(),dtoSend.getTypeOfAction());
			message=currentTurn.turn(dtoTurn);							//messaggio di come è stata eseguita l'azione
			if(message=="Hai finito il turno") {
				ControlEndTurn controlEndGame=new ControlEndTurn(game,turnNumber);		//controlla la fine della partita, se si la elimina e avvisa i giocatori
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
		return message;
	}
	public DTOGame completeTurn() {		//chiamato dal Thread che guarda il tempo nel caso non venga avvisato
		String message="";
		DTOGame dtoGame;
		//message=currentTurn.completeTurn();
		ControlResponse controlResponse=new ControlResponse();
		dtoGame=controlResponse.control(message);		//analizza la stringa tornata e crea l'oggetto da passare
		return dtoGame;			//ritorna al thread che lo ha chiamato l'oggetto da ritornare
	}
	
	public static void main(String[] args) {
		
		GameCreator gameCreator = GameCreator.getinstance();
		Game model = gameCreator.createGame("Galilei", 6, "Exagonal");
		Player player = model.getPlayers(0);
		Sector currentSector = model.getMap().getSector(new Coordinate (12,3));
		player.setSector(currentSector);
		
		
	}
}
