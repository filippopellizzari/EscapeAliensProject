package connection;

/**
 * This class is used meanwhile the game is in creation, the player's thread take the views and the messages from this class
 * @author Nicola
 *
 */

public class DetailsPlayers {
	private int numberOfPlayers;
	private String buffer;
	private ViewForPlayer[] view;
	private TypeOfMap mapType;
	private int gameId;
	private StatusCreation status;
	
	/**
	 * This constructor set the features of the new game to be created
	 * @param typeOfMapChoose
	 */
	
	public DetailsPlayers(TypeOfMap typeOfMapChoose) {
		this.mapType=typeOfMapChoose;
		this.numberOfPlayers = 0;
		this.status=StatusCreation.OPEN;
		
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
	
	/**
	 * If the buffer is null wait then return the buffer
	 * @return the buffer
	 * @throws InterruptedException 
	 */
	
	public synchronized String getBuffer() throws InterruptedException {	//se è vuoto fermati
		while(buffer==null) 
			this.wait();
		return buffer;
	}
	
	/**
	 * Set the buffer and notifies all the threads in wait
	 * @param buffer the buffer to set
	 */
	
	public synchronized void setBuffer(String buffer) {		//notifica a tutti i thread in attesa di messaggio
		this.buffer=null;
		this.buffer = buffer;
		this.notifyAll();
	}
	
	/**
	 * Takes the view and decreases by one the number of player, when the number is -1 notifies all
	 * @return the view
	 */
	
	public synchronized ViewForPlayer getView() {
		ViewForPlayer viewToSend= view[numberOfPlayers]; //il primo è il giocatore più alto e così via
		numberOfPlayers--;
		if(numberOfPlayers==-1) 
			this.notifyAll();	//notifica che tutti i giocatori hanno preso il loro numero
		return viewToSend;		//ritorna la view
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
	
	/**
	 * @return the status
	 */
	
	public StatusCreation getStatus() {
		return status;
	}
	
	/**
	 * @param status the status to set
	 */
	
	public synchronized void setStatus(StatusCreation status) {
		this.status = status;
		this.notifyAll();
	}
	
	/**
	 * Wait to delete untile the game creation is complete
	 * @throws InterruptedException
	 */
	
	public synchronized void deleteGame() throws InterruptedException {		//mette in wait il temporize che vuole eliminare la partita
		while(status==StatusCreation.CLOSED) 
			this.wait();
	}
	
	/**
	 * Wait until all the players have taken their view
	 * @throws InterruptedException
	 */
	
	public synchronized void allPlayersHaveTakeView() throws InterruptedException {
		while(numberOfPlayers!=-1) 
			this.wait();
	}	
}
