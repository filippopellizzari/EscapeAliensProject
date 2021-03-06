package rmi;

import java.rmi.RemoteException;

import connection.*;
import controller.ActionType;
import dto.*;

/**
 * This class contains all the action used to connects, sends a message to join a game, does an action, receives a
 * action from the pub-sub 
 * @author Nicola
 * @see Actions
 */

public class RMIRoom implements Actions{

	DatabasePlayersIdentification identifyConnection;
	DatabaseInscriptionsForGames dataBaseForSubscribe;
	ListOfStartedGame listOfStartedGame;
	
	/**
	 * Takes and instance of many singleton, the DatabasePlayerIdentification to understand what player is,
	 * databaseInscriptionForGames to send a new inscription, list of game to take the correct game
	 */
	
	public RMIRoom() {
		identifyConnection=DatabasePlayersIdentification.getinstance();
		dataBaseForSubscribe=DatabaseInscriptionsForGames.getinstance();		//accesso a thread che accetta le richieste
		listOfStartedGame=ListOfStartedGame.getinstance();
	}

	@Override
	public Token getToken() {
		int i=0;
		PlayerIdentification identificationToBeWrite;
		do{
			identificationToBeWrite=identifyConnection.getIdentification(i);
			if(identificationToBeWrite==null) {
				identificationToBeWrite=new PlayerIdentification(-1,0);
				identifyConnection.setIdentificationList(identificationToBeWrite, i);  //aggiorna il database
				return new Token(i);
			}
			i++;
		}while(i<10000);
		return null;
	}
	
	@Override
	public ViewForPlayer subscribeGame(TypeOfMap typeOfMap, Token token) throws RemoteException {
		ViewForPlayer myView=null;
		try {
			DetailsPlayers detailsYourGame;
			detailsYourGame=dataBaseForSubscribe.subscribe(typeOfMap);		//hai i dettagli della partita in corso
			putInWait(detailsYourGame);
			if(detailsYourGame.getBuffer()=="Partita pronta, Turno Giocatore 1") {
				DatabasePlayersIdentification identifyTypeOfConnection;
				identifyTypeOfConnection=DatabasePlayersIdentification.getinstance();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				myView=detailsYourGame.getView();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myView.getNumberPlayer()); //numero giocatore
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		return myView;
	}
	
	@Override
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException {
		DTOGame dtoGame=new DTOGame();
		try {
			PlayerIdentification identification=identifyConnection.getIdentification(token.getNumber());	//prendo l'identificatore del giocatore per avere il gioco
			int numberPlayer=identification.getNumberPlayer();
			dtoSend.setNumberPlayer(numberPlayer);		//metto il numero di giocatore
			GameDescription gameDescription;
			gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());
			if(dtoSend.getActionType()==ActionType.CHAT) {
				dtoGame.setReceiver(9);
				dtoGame.setActionType(ActionType.CHAT);
				dtoGame.setChat(dtoSend.getChat());
				dtoGame.setPlayerNumber(numberPlayer);
			}
			else {
				putInWaitAction(gameDescription);
				dtoGame = gameDescription.getController().doAnAction(dtoSend);
				gameDescription.setStatus();
			}
			if(dtoGame.getReceiver()==9|| dtoGame.getReceiver()==10) {
				gameDescription.getBroker().publish(dtoGame);
			}
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
		return dtoGame;
	}

	/**
	 * Waits if the game isn't ready to send the view
	 * @param detailsYourGame
	 * @throws InterruptedException
	 */
	
	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
	}
	
	/**
	 * Waits if the controller is busy
	 * @param gameDescription
	 * @throws InterruptedException
	 */
	
	private void putInWaitAction(GameDescription gameDescription) throws InterruptedException {
		gameDescription.getStatus();		//se è vuoto fermati e aspetta
	}

	@Override
	public DTOGame subscribe(Token token) throws RemoteException, InterruptedException {
		PlayerIdentification identification=identifyConnection.getIdentification(token.getNumber());
		GameDescription gameDescription;
		gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());
		DTOGame dtoGame=new DTOGame();
		dtoGame=gameDescription.getBroker().getPlayersBuffer(identification.getNumberPlayer()).getBuffer();
		if(dtoGame.getActionType()==ActionType.ENDGAME) {
			identifyConnection.getIdentification(token.getNumber()).setNumberGame(-1);	//numero partita
			identifyConnection.getIdentification(token.getNumber()).setNumberPlayer(0);
		}
		return dtoGame;
	}

}
