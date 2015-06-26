package socket;

/**
 * This is the interface that implements the pattern strategy in the client handler, 
 * to understand what response is requested
 * @author Nicola
 *
 */
public interface Processing {
	/**
	 * method to call
	 */
	public void start();
}
