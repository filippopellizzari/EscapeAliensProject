package connection;

/**
 * This thread cronometers the player turn, if timer stops the thread complete turn is called
 * @author Nicola
 *
 */

public class ThreadTemporize implements Runnable {
	private int time;
	private GameDescription gameDescription;
	
	/**
	 * This constructor sets the time and the game to cronometer
	 * @param time
	 * @param gameDescription
	 */

	public ThreadTemporize(int time, GameDescription gameDescription) {
		this.gameDescription = gameDescription;
		this.time = time;
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
			gameDescription.getController().setChangeTurn();	// notifico cambio di turno
			gameDescription.setStatus(); // libera il controller
			System.out.println("Sono il timer ho finito il turno");
		} catch (InterruptedException e) {
			System.err.print("Errore nel temporize del turno");
		}
	}
}
