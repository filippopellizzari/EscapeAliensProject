package pubSub;

import dto.DTOGame;

public class Broker {
	private PlayersBuffers[] playersBuffer;
	private String message;
	private int numberOfPlayers;

	public Broker(int numberOfPlayers) {
		this.numberOfPlayers=numberOfPlayers;
		playersBuffer=new PlayersBuffers[numberOfPlayers];
		for(int i=0;i<numberOfPlayers;i++) {
			playersBuffer[i]=new PlayersBuffers();
			this.message=null;
		}
	}	

	public void publish(DTOGame dtoGame){
		if(dtoGame.getReceiver()<8)  {			//solo il giocatore deve ricevere il messaggio
			playersBuffer[dtoGame.getPlayerNumber()].setBuffer(dtoGame);
		}
		else {
			dtoGame.setGameMessage(message);
			if(dtoGame.getReceiver()==9) {
				for(int i=0;i<numberOfPlayers;i++) {
					if(i==dtoGame.getPlayerNumber()) continue;
					playersBuffer[i].setBuffer(dtoGame);
				}
			}
			else
				message=dtoGame.getGameMessage(); 	//aggiungi il messaggio di gioco
		}
	}

	/**
	 * @return the playersBuffer
	 */
	public PlayersBuffers getPlayersBuffer(int numberPlayer) {
		return playersBuffer[numberPlayer];
	}
	
}
