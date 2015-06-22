package pubSub;

import java.util.ArrayList;
import java.util.List;

import controller.ActionType;
import dto.DTOGame;

/**
 * This is the player's buffer if the buffer is void no message must be send, otherwise there are one or more
 * message that the player has to receive
 * @author Nicola
 *
 */

public class PlayersBuffers {
	private List<DTOGame> buffer;
	private Broker broker;
	
	public PlayersBuffers(Broker broker) {
		this.buffer=new ArrayList<DTOGame>();
		this.broker=broker;
	}

	/**
	 * If the buffer is empty waits otherwise takes and removes the fist message, if the message
	 * is the end of game notifies this
	 * @return the buffer
	 * @throws InterruptedException 
	 */
	public synchronized DTOGame getBuffer() throws InterruptedException {
		while(buffer.size()==0) 
			this.wait();
		DTOGame dtoGame= buffer.remove(0);
		if(dtoGame.getActionType()==ActionType.ENDGAME) {
			broker.getGameDescription().endPlayer();
		}
		return dtoGame;
	}

	/**
	 * @param buffer the buffer to set
	 */
	public synchronized void setBuffer(DTOGame dtoGame) {
		buffer.add(dtoGame);
		notifyAll();
	}
	
}
