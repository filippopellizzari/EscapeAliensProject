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
		currentTurn = new Turn(game,currentPlayer);
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
			TurnDTO turnDTO=new TurnDTO(dtoSend.getCoordinate(),dtoSend.getTypeCard(),dtoSend.isAttack(),
					dtoSend.getEndTurn(),dtoSend.isUseCard(),dtoSend.isMove());
			message=currentTurn.turn(turnDTO);							//messaggio di come è stata eseguita l'azione
			if(message=="Hai finito il turno") {
				currentPlayer++;
				if(game.getPlayers().length==currentPlayer) {
					currentPlayer=0;		//giocatore a cui tocca
					turnNumber++;
					currentTurn=new Turn(game,currentPlayer);
				}
				ControlEndTurn controlEndGame=new ControlEndTurn(game,turnNumber);		//controlla la fine della partita, se si la elimina e avvisa i giocatori
			}
		}
		return message;
	}
	public DTOGame completTurn() {		//chiamato dal Thread che guarda il tempo nel caso non venga avvisato
		String message="";
		DTOGame dtoGame;
		message=currentTurn.completeTurn();
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
