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
			if(playerWithRelativeConnection.get(i).getMapType()==typeOfMapChoose) {	//iscrizione già in corso
				playerWithRelativeConnection.get(i).setNumberOfPlayers();
				DetailsPlayers gameToBeCreated=playerWithRelativeConnection.get(i);
				if(playerWithRelativeConnection.get(i).getNumberOfPlayers()==7) {
					removeGame(i);		//rimuovi il gioco
					for(int j=0;j<list.size();j++) {					//rimuovi il temporize assegnato
						if(list.get(j).getName()==typeOfMapChoose.getMapName()) {	
							Thread toRemove=list.remove(j);				//rimuovi in thread temporize dalla lista e fermalo
							toRemove.stop();
						}
					}
				}
				return gameToBeCreated;		//torna riferimento a dati gioco in creazione
			}
		}
		playerWithRelativeConnection.add(new DetailsPlayers(typeOfMapChoose));		//nuovo gioco
		Thread temporize=new Thread(new Temporize(60,this,typeOfMapChoose),typeOfMapChoose.getMapName());		//temporize con nome
		list.add(temporize);
		temporize.start();
		return playerWithRelativeConnection.get(2);
	}
	
	public synchronized void  removeGame(int numberGameToCreate) {
		Thread newGame=new Thread(new ThreadTimeCreatorGame(playerWithRelativeConnection.remove(numberGameToCreate)));
		newGame.start();
	}

	/**
	 * @return the playerWithRelativeConnection
	 */
	public List<DetailsPlayers> getPlayerWithRelativeConnection() {
		return playerWithRelativeConnection;
	}
}