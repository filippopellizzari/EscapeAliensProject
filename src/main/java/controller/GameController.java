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
	private int numberOfPlayer;
	

	public GameController(String mapName, int totPlayers, String typeMap) {
		numberOfGames++;
		this.numberOfThisGame = numberOfGames;
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, totPlayers, typeMap);
		this.currentTurn = new Turn(game,numberOfPlayer);
		this.numberOfPlayer = 0;
		this.setTurnNumber(1);
	}
	
	/**
	 * @return the numberOfThisGame
	 */
	
	public int getNumberOfThisGame() {
		return numberOfThisGame;
	}
	
	
	public int getTurnNumber() {
		return turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public String doAnAction(DTOSend dtoSend) {
		String message="";
		//controlla validità dati passati
		if(new ControlDataReceived().verify(dtoSend,numberOfPlayer, game).equals("OK")) {//se la risposta è ok fa la verifica del turno
			DTOTurn turnDTO = new DTOTurn(dtoSend.getCoordinate(),dtoSend.getItemCardType(),dtoSend.isAttack(),
					dtoSend.getEndTurn(),dtoSend.wantsToUseItem(),dtoSend.isMove());
			message = currentTurn.turn(turnDTO);	//messaggio di come è stata eseguita l'azione
			if(message.equals("Hai finito il turno")) {
				numberOfPlayer++;
				if(game.getPlayers().length == numberOfPlayer) {
					numberOfPlayer=0;		//giocatore a cui tocca
					setTurnNumber(getTurnNumber() + 1);
					currentTurn = new Turn(game,numberOfPlayer);
				}
				//ControlEndTurn controlEndGame = new ControlEndTurn(game,turnNumber);		//controlla la fine della partita, se si la elimina e avvisa i giocatori
			}
		}
		return message;
	}
	public DTOGame completeTurn() {		//chiamato dal Thread che guarda il tempo nel caso non venga avvisato
		String message = currentTurn.completeTurn();
		DTOGame dtoGame = new ControlResponse().control(message);		//analizza la stringa tornata e crea l'oggetto da passare
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
