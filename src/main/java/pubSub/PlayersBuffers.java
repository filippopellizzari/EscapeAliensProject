package pubSub;

import java.util.ArrayList;
import java.util.List;

import dto.DTOGame;

/**
 * This is the player's buffer if the buffer is void no message must be send, otherwise there are one or more
 * message that the player has to receive
 * @author Nicola
 *
 */

public class PlayersBuffers {
	private List<DTOGame> buffer;

	public PlayersBuffers() {
		this.buffer=new ArrayList<DTOGame>();
	}

	/**
	 * @return the buffer
	 * @throws InterruptedException 
	 */
	public synchronized DTOGame getBuffer() throws InterruptedException {
		while(buffer.size()==0) 
			this.wait();
		return buffer.remove(0);
	}

	/**
	 * @param buffer the buffer to set
	 */
	public synchronized void setBuffer(DTOGame dtoGame) {
		buffer.add(dtoGame);
		notifyAll();
	}
	
}
