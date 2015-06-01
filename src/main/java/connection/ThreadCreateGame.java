package connection;

import java.util.ArrayList;
import java.util.List;

import socket.ClientHandlerChooseGameSocket;

public class ThreadCreateGame {
	Integer Fermi=0;
	Integer Galilei=1;
	Integer Galvani=2;
	List<DetailsPlayers> playerWithRelativeConnection;
	int counter=0;
	
	private static ThreadCreateGame instance = new ThreadCreateGame();
	
	private ThreadCreateGame() {
		playerWithRelativeConnection=new ArrayList<DetailsPlayers>();
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
		if(playerWithRelativeConnection.size()<=numberOfGameToSubscribe) {
			numberOfGameToSubscribe=playerWithRelativeConnection.size();	//ora punta alla fine della lista per il nuovo gioco che sarà creato
			Thread newGame=new Thread(new ThreadTimeCreatorGame(this,typeOfMapChoose,counter+1));	//crea un nuovo thread
			newGame.start();		//fallo partire
			playerWithRelativeConnection.add(new DetailsPlayers());
			playerWithRelativeConnection.get(numberOfGameToSubscribe).setNumberOfPlayers();
			counter++;
			playerWithRelativeConnection.get(numberOfGameToSubscribe).setGameId(counter);
			playerWithRelativeConnection.get(numberOfGameToSubscribe).setSocketPlayers(clientHandlerChooseGameSocket);
			numberOfGameToSubscribe=playerWithRelativeConnection.size()-1;
		}
		else {

			playerWithRelativeConnection.get(numberOfGameToSubscribe).setNumberOfPlayers();	//aggiungi giocatore
			playerWithRelativeConnection.get(numberOfGameToSubscribe).setSocketPlayers(clientHandlerChooseGameSocket);
			if(playerWithRelativeConnection.get(numberOfGameToSubscribe).getNumberOfPlayers()==7) {
				numberOfGameToSubscribe=playerWithRelativeConnection.size();		//new number
				removeThreadToCreateGame(playerWithRelativeConnection.get(numberOfGameToSubscribe).getGameId());
			}
		}
	}
	public synchronized void  removeThreadToCreateGame(int gameIdentification) {
		boolean condizione=false;
		int count=0;
		do {
			if(playerWithRelativeConnection.get(count).getGameId()==gameIdentification) 
				condizione=true;
			else count++;
		}while(condizione==false);
		if(count==Fermi) Fermi=playerWithRelativeConnection.size();
		else {
			if(Fermi==0) Fermi=0;
			else Fermi--;
		}
		if(count==Galilei) Galilei=playerWithRelativeConnection.size();
		else {
			if(Galilei==0) Galilei=0;
			else Galilei--;
		}
		if(count==Galvani) Galvani=playerWithRelativeConnection.size();
		else {
			if(Galvani==0) Galvani=0;
			else Galvani--;
		}
		playerWithRelativeConnection.remove(count);	//cancella il gioco pronto
	}

	/**
	 * @return the playerWithRelativeConnection
	 */
	public DetailsPlayers getPlayerWithRelativeConnection(int gameId) {
		for(int i=0;i<playerWithRelativeConnection.size();i++) {
			if(playerWithRelativeConnection.get(i).getGameId()==gameId)
				return playerWithRelativeConnection.get(i);
		}
		return null;
	}
	
	
}
