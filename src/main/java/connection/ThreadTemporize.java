package connection;

/**
 * This thread cronometers the player turn, if timer stops the thread complete turn is called
 * @author Nicola
 *
 */

public class ThreadTemporize implements Runnable {
	private int time;
	private GameDescription gameDescription;
	private int turn = 0;
	private int numberPlayer = 0;
	
	/**
	 * This constructor sets the time and the game to cronometer
	 * @param time
	 * @param gameDescription
	 * @param numberPlayer 
	 * @param turn 
	 */

	public ThreadTemporize(int time, GameDescription gameDescription, int turn, int numberPlayer) {
		this.gameDescription = gameDescription;
		this.time = time;
		this.turn=turn;
		this.numberPlayer=numberPlayer;
	}
	
	/**
	 * Wait for tot seconds then change the game's turn
	 */

	@Override
	public void run() {
		try {
			System.out.println("Sono il timer mi metto in attesa");
			Thread.sleep(time * 1000);
			gameDescription.getStatus();
			if(gameDescription.getController().getRound()==turn &&
					gameDescription.getController().getCurrentNumberPlayer()==numberPlayer) {		//se il turno è lo stesso segnala
				gameDescription.getController().setChangeTurn();	// notifico cambio di turno
				gameDescription.setStatus(); // libera il controller
			}
			System.out.println("Sono il timer ho finito il turno");
		} catch (InterruptedException e) {
			System.err.print("Errore nel temporize del turno");
		}
	}
}