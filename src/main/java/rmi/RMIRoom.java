package rmi;

import java.rmi.RemoteException;

import connection.*;
import dto.DTOGame;
import dto.DTOSend;

public class RMIRoom implements Actions{
	
	@Override
	public Token getToken() throws RemoteException{
		IdentifyTypeOfConnection identifyConnection;
		identifyConnection=IdentifyTypeOfConnection.getinstance();
		int i=0;
		Identification identificationToBeWrite;
		do{
			identificationToBeWrite=identifyConnection.getIdentification(i);
			if(identificationToBeWrite==null) {
				identificationToBeWrite=new Identification(i,-1,0);
				identifyConnection.setIdentificationList(identificationToBeWrite, i);  //aggiorna il database
				return new Token(i);		//nuovo token
			}
			i++;
		}while(i<10000);
		return null;
	}

	@Override
	public ViewForPlayer subscribeGame(TypeOfMap typeOfMap, Token token) throws RemoteException{
		ViewForPlayer myView=null;
		try {
			DatabaseCreateGame dataBaseForSubscribe;
			dataBaseForSubscribe=DatabaseCreateGame.getinstance();		//accesso a thread che accetta le richieste
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
	public DTOGame doAnAction(DTOSend dtoSend, Token token) throws RemoteException{
		DTOGame dtoGame=null;
		try {
			GameDescription gameDescription;
			ListOfStartedGame listOfStartedGame=ListOfStartedGame.getinstance();
			gameDescription=listOfStartedGame.getNumberGameDescription(token.getNumber());
			dtoGame = gameDescription.getController().doAnAction(dtoSend);
			if(dtoGame.getDestination()==9) {
				//aggiungere la parte di invio al broker
			}
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println(e.getMessage());
		}
		return dtoGame;
	}
	
	private void putInWait(DetailsPlayers detailsYourGame) throws InterruptedException {
		System.out.println("Sono il thread connessione mi metto in wait");
		detailsYourGame.getBuffer();		//se è vuoto fermati e aspetta
		System.out.println("Sono il thread connessione mi sveglio dallo wait");
	}
}
