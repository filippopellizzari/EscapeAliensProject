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
	
	public GameController(String mapName, int numberOfPlayers, String typeMap) {
		numberOfGames++;
		this.numberOfThisGame=numberOfGames;
		GameCreator gameCreator = GameCreator.getinstance();
		this.game = gameCreator.createGame(mapName, numberOfPlayers, typeMap);
		currentTurn = new Turn(numberOfPlayers, game);
	}
	
	/**
	 * @return the numberOfThisGame
	 */
	
	public int getNumberOfThisGame() {
		return numberOfThisGame;
	}
	public DTOGame doAnAction(DTOSend dtoSend) {
		String message="";
		DTOGame dtoGame;
		ControlDataRiceived control=new ControlDataRiceived();			//controlla validità dati passati
		if(control.verify(dtoSend,currentTurn.getNumberPlayer(), game)=="OK") {								//se la risposta è ok fa la verifica del turno
			TurnDTO turnDTO=new TurnDTO(dtoSend.getCoordinate(),dtoSend.getTypeCard(),dtoSend.isAttack(),
					dtoSend.getEndTurn(),dtoSend.isUseCard(),dtoSend.isMove());
			message=currentTurn.turn(turnDTO);							//messaggio di come è stata eseguita l'azione
			ControlResponse controlResponse=new ControlResponse();
			dtoGame=controlResponse.control(message);		//analizza la stringa tornata e crea l'oggetto da passare
		}
		else {				//ritorna il messaggio di errore al client
			dtoGame=new DTOGame(null,null,false,false,false,null,message,false,false,false);
		}
		return dtoGame;			//ritorna al thread che lo ha chiamato l'oggetto da ritornare
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
