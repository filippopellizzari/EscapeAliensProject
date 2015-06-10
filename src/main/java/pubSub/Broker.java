package pubSub;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

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
		dtoGame.setGameMessage(message);
		if(dtoGame.getDestination()==9) {
			for(int i=0;i<numberOfPlayers;i++) {
				if(i==dtoGame.getPlayer()) continue;
				playersBuffer[i].setBuffer(dtoGame);
			}
		}
		else
			message=dtoGame.getGameMessage(); 	//aggiungi il messaggio di gioco
	}
}
