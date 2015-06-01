package connection;

import java.util.ArrayList;
import java.util.List;

import socket.ClientHandlerChooseGameSocket;

public class ThreadCreateGame {
	Integer Fermi=0;
	Integer Galilei=1;
	Integer Galvani=2;
	List<Thread> listOfGameWaitForConnection;
	List<Integer> listOfPlayersWaitForConnection;
	List<Integer> gameId;
	int counter=0;
	
	private static ThreadCreateGame instance = new ThreadCreateGame();
	
	private ThreadCreateGame() {
		listOfGameWaitForConnection=new ArrayList<Thread>();
		listOfPlayersWaitForConnection=new ArrayList<Integer>(); 
		gameId=new ArrayList<Integer>();
	}
	
	public static ThreadCreateGame getinstance() {
		return instance;
	}
	
	public synchronized String subscribe(TypeOfMap typeOfMapChoose, ClientHandlerChooseGameSocket clientHandlerChooseGameSocket) {
		int numberOfGameToSubscribe;
		switch(typeOfMapChoose.getMapName()) {
			case "Fermi" : numberOfGameToSubscribe=Fermi;
			subscribing(Fermi,typeOfMapChoose,clientHandlerChooseGameSocket);
			break;
			case "Galilei" : numberOfGameToSubscribe=Galilei;
			subscribing(Galilei,typeOfMapChoose,clientHandlerChooseGameSocket);
			break;
			case "Galvani" : numberOfGameToSubscribe=Galvani;
			subscribing(Galvani,typeOfMapChoose,clientHandlerChooseGameSocket);
			break;
			default : numberOfGameToSubscribe=0;
			break;
		}
		return "Iscrizione Ricevuta";
	}
	
	private synchronized void subscribing(Integer numberOfGameToSubscribe, TypeOfMap typeOfMapChoose,
			ClientHandlerChooseGameSocket clientHandlerChooseGameSocket) {
		if(listOfGameWaitForConnection.size()<=numberOfGameToSubscribe) {
			listOfGameWaitForConnection.add(new Thread(new ThreadTimeCreatorGame(this,typeOfMapChoose,
					clientHandlerChooseGameSocket,counter+1)));	//crea un nuovo thread
			listOfGameWaitForConnection.get(numberOfGameToSubscribe).start();		//fallo partire
			listOfPlayersWaitForConnection.set(numberOfGameToSubscribe, 1);	//nuova iscrizione con 1 giocatore
			counter++;
			gameId.add(counter);
			numberOfGameToSubscribe=listOfGameWaitForConnection.size()-1;
		}
		else {
			int numberBefore=listOfPlayersWaitForConnection.get(numberOfGameToSubscribe);	//numero prima di giocatori
			listOfPlayersWaitForConnection.set(numberOfGameToSubscribe, numberBefore+1);
			if(listOfPlayersWaitForConnection.get(numberOfGameToSubscribe)==8) {
				numberOfGameToSubscribe=listOfGameWaitForConnection.size();		//new number
				removeThreadToCreateGame(gameId.get(numberOfGameToSubscribe));
			}
		}
	}
	public synchronized void  removeThreadToCreateGame(Integer gameIdentification) {
		boolean condizione=false;
		int count=0;
		do {
			if(gameId.get(count)==gameIdentification) 
				condizione=true;
			else count++;
		}while(condizione==false);
		if(count==Fermi) Fermi=listOfGameWaitForConnection.size();
		else {
			if(Fermi==0) Fermi=0;
			else Fermi--;
		}
		if(count==Galilei) Galilei=listOfGameWaitForConnection.size();
		else {
			if(Galilei==0) Galilei=0;
			else Galilei--;
		}
		if(count==Galvani) Galvani=listOfGameWaitForConnection.size();
		else {
			if(Galvani==0) Galvani=0;
			else Galvani--;
		}
		listOfGameWaitForConnection.remove(count);	//cancella il gioco pronto
		listOfPlayersWaitForConnection.remove(count);
	}
}
