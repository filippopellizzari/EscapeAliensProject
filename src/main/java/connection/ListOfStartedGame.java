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
	 * @param gameDescriptionList the gameDescriptionList to set
	 */
	public synchronized int addGameDescription(GameDescription gameDescription) {
		for(int i=0;i<1000;i++) {
			if(gameDescriptionList[i]==null) {
				System.out.println("Aggiunto il gioco: "+i+" hai giochi esistenti");
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
