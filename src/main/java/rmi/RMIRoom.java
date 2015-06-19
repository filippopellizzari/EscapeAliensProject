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

	IdentifyTypeOfConnection identifyConnection;
	DatabaseCreateGame dataBaseForSubscribe;
	ListOfStartedGame listOfStartedGame;
	
	public RMIRoom() {
		identifyConnection=IdentifyTypeOfConnection.getinstance();
		dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
		listOfStartedGame=ListOfStartedGame.getinstance();
	}

	@Override
	public Token getToken() {
		int i=0;
		Identification identificationToBeWrite;
		do{
			identificationToBeWrite=identifyConnection.getIdentification(i);
			if(identificationToBeWrite==null) {
				identificationToBeWrite=new Identification(-1,0);
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
				IdentifyTypeOfConnection identifyTypeOfConnection;
				identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
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
			Identification identification=identifyConnection.getIdentification(token.getNumber());	//prendo l'identificatore del giocatore per avere il gioco
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

	
	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione aspetto il buffer");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione ricevuto il buffer");
	}
	
	private void putInWaitAction(GameDescription gameDescription) throws InterruptedException {
		System.out.println("Sono il thread connessione");
		gameDescription.getStatus();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio");
	}

	@Override
	public DTOGame subscribe(Token token) throws RemoteException, InterruptedException {
		Identification identification=identifyConnection.getIdentification(token.getNumber());
		GameDescription gameDescription;
		gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());
		DTOGame dtoGame=new DTOGame();
		dtoGame=gameDescription.getBroker().getPlayersBuffer(identification.getNumberPlayer()).getBuffer();
		return dtoGame;
	}

}
