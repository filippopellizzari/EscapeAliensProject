package connection;

public class DetailsPlayers {
	private int numberOfPlayers;
	private String buffer;
	private ViewForPlayer[] view;
	private TypeOfMap mapType;
	private int gameId;
	
	public DetailsPlayers(TypeOfMap typeOfMapChoose) {
		this.mapType=typeOfMapChoose;
		this.numberOfPlayers = 0;
		
	}
	/**
	 * @return the numberOfPlayers
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public void setNumberOfPlayers() {
		this.numberOfPlayers++;
	}
	
	public synchronized int takeNumberOfPlayer() {
		int number=numberOfPlayers;
		numberOfPlayers--;
		if(numberOfPlayers==-1) notifyAll();	//notifica che tutti i giocatori hanno preso il loro numero
		return number;
	}
	/**
	 * @return the buffer
	 */
	public String getBuffer() {
		return buffer;
	}
	/**
	 * @param buffer the buffer to set
	 */
	public synchronized void setBuffer(String buffer) {		//notifica a tutti i thread in attesa di messaggio
		this.buffer=null;
		this.buffer = buffer;
		notifyAll();
	}
	/**
	 * @return the view
	 */
	public ViewForPlayer getView(int number) {
		return view[number];
	}
	/**
	 * @param view the view to set
	 */
	public void setView(ViewForPlayer[] view) {
		this.view = view;
	}
	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}
	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	/**
	 * @return the mapType
	 */
	public TypeOfMap getMapType() {
		return mapType;
	}
	
	
}
