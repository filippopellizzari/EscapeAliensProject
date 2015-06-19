package connection;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to contains all the game open to be join by other player, when the game is created is placed in an other way
 * @author Nicola
 *
 */

public class DatabaseCreateGame {
	List<DetailsPlayers> playerWithRelativeConnection;
	private static DatabaseCreateGame instance = new DatabaseCreateGame();
	
	/**
	 * Create a new database
	 */
	
	private DatabaseCreateGame() {
		playerWithRelativeConnection=new ArrayList<DetailsPlayers>();
	}
	
	/**
	 * Return a istance of this class
	 * @return
	 */
	
	public static DatabaseCreateGame getinstance() {
		return instance;
	}
	
	/**
	 * This method is used to subscribe a new game
	 * @param typeOfMapChoose
	 * @return the corresponding details player
	 */
	
	public synchronized DetailsPlayers subscribe(TypeOfMap typeOfMapChoose) {
		System.out.println(playerWithRelativeConnection.size());
		for(int i=0;i<playerWithRelativeConnection.size();i++) {
			if(playerWithRelativeConnection.get(i).getStatus()==StatusCreation.OPEN &&
					playerWithRelativeConnection.get(i).getMapType().equals(typeOfMapChoose)) {	//iscrizione già in corso
				System.out.println("aggiunto giocatore a una partita");
				playerWithRelativeConnection.get(i).setNumberOfPlayers();
				if(playerWithRelativeConnection.get(i).getNumberOfPlayers()==7) 	//blocca il gioco
					blockGame(i);	//iscrizione chiusa
				return playerWithRelativeConnection.get(i);			//passa il gioco
			}
		}
		playerWithRelativeConnection.add(new DetailsPlayers(typeOfMapChoose));		//nuovo gioco
		Thread temporize=new Thread(new Temporize(20,this,typeOfMapChoose));		//temporize con nome
		temporize.start();
		return playerWithRelativeConnection.get(playerWithRelativeConnection.size()-1);		//dagli il details player appena creato
	}
	
	/**
	 * Block a game when time is finished so no other player can join this game after that
	 * @param numberGame
	 */
	
	public synchronized void blockGame(int numberGame) {
		System.out.println("Chiusura iscrizione partita");
		if(playerWithRelativeConnection.get(numberGame).getStatus()==StatusCreation.OPEN) {	//controlla che sia aperto
			playerWithRelativeConnection.get(numberGame).setStatus(StatusCreation.CLOSED);
			Thread newGame=new Thread(new ThreadTimeCreatorGame(playerWithRelativeConnection.get(numberGame)));
			newGame.start();
		}
	}
	
	/**
	 * Remove a game from the database
	 * @param numberGame
	 */
	
	public synchronized void  removeGame(int numberGame) { //rimuovi il temporize assegnato
		playerWithRelativeConnection.remove(numberGame);		//rimuovi i dati
		System.out.println("Remove effettuata");
	}

	/**
	 * @return the playerWithRelativeConnection
	 */
	public List<DetailsPlayers> getPlayerWithRelativeConnection() {
		return playerWithRelativeConnection;
	}
}


/*&& */