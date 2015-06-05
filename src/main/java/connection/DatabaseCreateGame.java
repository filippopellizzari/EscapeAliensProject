package connection;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCreateGame {
	List<DetailsPlayers> playerWithRelativeConnection;
	private static DatabaseCreateGame instance = new DatabaseCreateGame();
	private List<Thread> list;
	
	private DatabaseCreateGame() {
		playerWithRelativeConnection=new ArrayList<DetailsPlayers>();
		list=new ArrayList<Thread>();
	}
	
	public static DatabaseCreateGame getinstance() {
		return instance;
	}
	
	public synchronized DetailsPlayers subscribe(TypeOfMap typeOfMapChoose) {
		for(int i=0;i<playerWithRelativeConnection.size();i++) {
			if(playerWithRelativeConnection.get(i).getMapType()==typeOfMapChoose 
					&& playerWithRelativeConnection.get(i).getStatus()==StatusCreation.OPEN) {	//iscrizione già in corso
				System.out.println("aggiunto giocatore a una partita");
				playerWithRelativeConnection.get(i).setNumberOfPlayers();
				if(playerWithRelativeConnection.get(i).getNumberOfPlayers()==7) 	//blocca il gioco
					blockGame(i);	//iscrizione chiusa
				return playerWithRelativeConnection.get(i);			//passa il gioco
			}
		}
		playerWithRelativeConnection.add(new DetailsPlayers(typeOfMapChoose));		//nuovo gioco
		Thread temporize=new Thread(new Temporize(10,this,typeOfMapChoose));		//temporize con nome
		list.add(temporize);		//genera il thread che lo crea
		temporize.start();
		return playerWithRelativeConnection.get(playerWithRelativeConnection.size()-1);		//dagli il details player appena creato
	}
	
	public synchronized void blockGame(int numberGame) {
		if(playerWithRelativeConnection.get(numberGame).getStatus()==StatusCreation.OPEN) {	//controlla che sia aperto
			playerWithRelativeConnection.get(numberGame).setStatus(StatusCreation.CLOSED);
			Thread newGame=new Thread(new ThreadTimeCreatorGame(playerWithRelativeConnection.get(numberGame)));
			newGame.start();
		}
	}
	
	public synchronized void  removeGame(int numberGame) { //rimuovi il temporize assegnato
		playerWithRelativeConnection.remove(numberGame);		//rimuovi i dati
		Thread toRemove=list.remove(numberGame);				//rimuovi in thread temporize dalla lista e fermalo
		System.out.println("Remove effettuata");
	}

	/**
	 * @return the playerWithRelativeConnection
	 */
	public List<DetailsPlayers> getPlayerWithRelativeConnection() {
		return playerWithRelativeConnection;
	}
}