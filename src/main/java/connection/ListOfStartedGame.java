package connection;

/**
 * This class is a singleton and contains all the started game and the methods to creates, takes and removes a game
 * @author Nicola
 *
 */

public class ListOfStartedGame {
	
	private static ListOfStartedGame instance = new ListOfStartedGame();
	private GameDescription[] gameDescriptionList;

	/**
	 * Creates a new array of void games description
	 */
	public ListOfStartedGame() {
		this.gameDescriptionList=new GameDescription[1000];
	}
	
	/**
	 * Returns a istance of this class
	 * @return
	 */
	public static ListOfStartedGame getinstance() {
		return instance;
	}
	
	/**
	 * Takes the game with a specific number
	 * @param number
	 * @return
	 */
	public GameDescription getNumberGameDescription(int number) {
		return gameDescriptionList[number];
	}

	/**
	 * Adds a game in the class
	 * @param gameDescriptionList the gameDescriptionList to set
	 */
	public synchronized int addGameDescription(GameDescription gameDescription) {
		for(int i=0;i<1000;i++) {
			if(gameDescriptionList[i]==null) {
				System.out.println("Aggiunto il gioco: "+i+" ai giochi esistenti");
				Thread deleteGame=new Thread(new DeleteGame(i));
				deleteGame.start();
				gameDescriptionList[i]=gameDescription;
				return i;		//ritorna numero del gioco
			}
		}
		return 1001;
	}
	
	/**
	 * Removes a game from the class and resets the token of the corresponding players
	 * @param number
	 * @throws InterruptedException 
	 */
	public synchronized void removeGameDescription(int number) throws InterruptedException{
		System.out.println("Ho cancellato la partita: "+number);
		gameDescriptionList[number]=null;
	}

	/**
	 * @return the gameDescriptionList
	 */
	public synchronized GameDescription getGameDescriptionList(int number) {
		return gameDescriptionList[number];
	}
}
