package pubSub;

import model.ItemCardType;
import connection.GameDescription;
import controller.ActionType;
import dto.DTOGame;

/**
 * This class is the pub-sub of the game, receives a message from the game and saves it in the players' buffers
 * @author Nicola
 *
 */

public class Broker {
	private PlayersBuffers[] playersBuffer;
	private ItemCardType item;
	private int numberOfPlayers;
	private GameDescription gameDescription;
	private boolean itemFull;
	
	/**
	 * This constructor creates x number of buffer, one for each player
	 * @param numberOfPlayers
	 */

	public Broker(int numberOfPlayers, GameDescription gameDescription) {
		this.numberOfPlayers=numberOfPlayers;
		playersBuffer=new PlayersBuffers[numberOfPlayers];
		this.gameDescription=gameDescription;
		for(int i=0;i<numberOfPlayers;i++) {
			playersBuffer[i]=new PlayersBuffers(this);
		}
		this.itemFull=false;
	}	
	
	/**
	 * This method is used to save a message in the broker, the broker analizes the message
	 * and saves this in the players' buffer 
	 * @param dtoGame
	 */

	public void publish(DTOGame dtoGame){
		if(dtoGame.getReceiver()<8)  {			//solo il giocatore deve ricevere il messaggio
			playersBuffer[dtoGame.getPlayerNumber()].setBuffer(dtoGame);
		}
		else {
			if(itemFull && dtoGame.getActionType()==ActionType.SELECTSECTORNOISE ) {
				dtoGame.setItemCardType(item);
				itemFull=false;
			}
			if(dtoGame.getReceiver()==9) {
				for(int i=0;i<numberOfPlayers;i++) {
					if(i==dtoGame.getPlayerNumber());
					else {
						playersBuffer[i].setBuffer(dtoGame);
					}
				}
			}
			else {
				if(dtoGame.getItemCardType()!=null)		//ha pescato una carta
				{
					item=dtoGame.getItemCardType();
					itemFull=true;
				}
			}
		}
	}

	/**
	 * @return the playersBuffer
	 */
	
	public PlayersBuffers getPlayersBuffer(int numberPlayer) {
		return playersBuffer[numberPlayer];
	}

	/**
	 * @return the gameDescription
	 */
	
	public GameDescription getGameDescription() {
		return gameDescription;
	}
}
