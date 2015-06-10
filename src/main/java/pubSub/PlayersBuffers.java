package pubSub;

import java.util.ArrayList;
import java.util.List;

import dto.DTOGame;

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
		if(buffer.size()==0) this.wait();
		return buffer.remove(0);
	}

	/**
	 * @param buffer the buffer to set
	 */
	public void setBuffer(DTOGame dtoGame) {
		buffer.add(dtoGame);
		notifyAll();
	}
	
}
