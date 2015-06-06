package connection;

public class DetailsPlayers {
	private int numberOfPlayers;
	private String buffer;
	private ViewForPlayer[] view;
	private TypeOfMap mapType;
	private int gameId;
	private StatusCreation status;
	
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
	 * @return the buffer
	 * @throws InterruptedException 
	 */
	public synchronized String getBuffer() throws InterruptedException {	//se è vuoto fermati
		while(buffer==null) 
			this.wait();
		return buffer;
	}
	/**
	 * @param buffer the buffer to set
	 */
	public synchronized void setBuffer(String buffer) {		//notifica a tutti i thread in attesa di messaggio
		this.buffer=null;
		this.buffer = buffer;
		this.notifyAll();
	}
	/**
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
	public synchronized void deleteGame() throws InterruptedException {		//mette in wait il temporize che vuole eliminare la partita
		while(status==StatusCreation.CLOSED) 
			this.wait();
	}
	public synchronized void allPlayersHaveTakeView() throws InterruptedException {
		while(numberOfPlayers!=-1) 
			this.wait();
		
	}	
}
