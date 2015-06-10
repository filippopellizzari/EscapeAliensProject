package rmi;

import java.rmi.RemoteException;
import java.util.List;

import pubSub.Broker;
import connection.*;
import dto.*;

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
	public void subscribeGame(TypeOfMap typeOfMap, Token token, SetClientParameter setParameter) throws RemoteException {
		ViewForPlayer myView=null;
		try {
			DetailsPlayers detailsYourGame;
			detailsYourGame=dataBaseForSubscribe.subscribe(typeOfMap);		//hai i dettagli della partita in corso
			setParameter.setBuffer("Iscrizione ricevuta");
			putInWait(detailsYourGame);
			setParameter.setBuffer(detailsYourGame.getBuffer());
			if(detailsYourGame.getBuffer()=="Partita pronta, Turno Giocatore 1") {
				IdentifyTypeOfConnection identifyTypeOfConnection;
				identifyTypeOfConnection=IdentifyTypeOfConnection.getinstance();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberGame(detailsYourGame.getGameId());	//numero partita
				myView=detailsYourGame.getView();
				identifyTypeOfConnection.getIdentification(token.getNumber()).setNumberPlayer(myView.getNumberPlayer()); //numero giocatore
				setParameter.setView(myView);
			}
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException {
		DTOGame dtoGame=null;
		try {
			Identification identification=identifyConnection.getIdentification(token.getNumber());	//prendo l'identificatore del giocatore per avere il gioco
			int numberPlayer=identification.getNumberPlayer();
			dtoSend.setNumberPlayer(numberPlayer);
			GameDescription gameDescription;
			gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());
			putInWait2(gameDescription);
			dtoGame = gameDescription.getController().doAnAction(dtoSend);
			gameDescription.setStatus(StatusController.FREE);
			if(dtoGame.getDestination()==9|| dtoGame.getDestination()==10) {
				gameDescription.getBroker().publish(dtoGame);
			}
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InterruptedException e) {
			System.err.println(e.getMessage());
		}
		return dtoGame;
	}

	
	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}
	
	private void putInWait2(GameDescription gameDescription) throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		gameDescription.getStatus();		//se è vuoto fermati e aspetta
		gameDescription.setStatus(StatusController.BUSY);
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}

	@Override
	public void subscribe(SetClientParameter setParameter, Token token) throws RemoteException, InterruptedException {
		Identification identification=identifyConnection.getIdentification(token.getNumber());
		GameDescription gameDescription;
		gameDescription=listOfStartedGame.getNumberGameDescription(identification.getNumberGame());
		DTOGame dtoGame=new DTOGame();
		do {
			dtoGame=gameDescription.getBroker().getPlayersBuffer(identification.getNumberPlayer()).getBuffer();
			setParameter.setDTOGameList(dtoGame);
		}while(dtoGame.getGameMessage()!="Partita conclusa");
	}

}
