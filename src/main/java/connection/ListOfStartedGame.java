package connection;

public class ListOfStartedGame {
	
	private static ListOfStartedGame instance = new ListOfStartedGame();
	private GameDescription[] gameDescriptionList;

	public ListOfStartedGame() {
		this.gameDescriptionList=new GameDescription[1000];
	}
	
	public static ListOfStartedGame getinstance() {
		return instance;
	}
	
	public GameDescription getNumberGameDescription(int number) {
		return gameDescriptionList[number];
	}
	/**
	 * @return the gameDescriptionList
	 */
	public synchronized GameDescription[] getGameDescriptionList() {
		return gameDescriptionList;
	}

	/**
	 * @param gameDescriptionList the gameDescriptionList to set
	 */
	public synchronized int addGameDescription(GameDescription gameDescription) {
		for(int i=0;i<1000;i++) {
			if(gameDescriptionList[i]==null) {
				gameDescriptionList[i]=gameDescription;
				return i;		//ritorna numero del gioco
			}
		}
		return 1001;
	}
	
	public synchronized void removeGameDescription(int number) {
		gameDescriptionList[number]=null;
	}
}
