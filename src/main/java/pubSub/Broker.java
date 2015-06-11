package pubSub;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

import dto.DTOGame;

public class Broker implements Runnable{
	private PlayersBuffers[] playersBuffer;
	private DTOGame messageToBeDispatched;
	private ArrayList<BrokerThread> subscribers = new ArrayList<BrokerThread>();
	private String topic;
	private int numberOfPlayers;

	public Broker(int numberOfPlayers) {
		this.numberOfPlayers=numberOfPlayers;
		playersBuffer=new PlayersBuffers[numberOfPlayers];
		for(int i=0;i<numberOfPlayers;i++) {
			playersBuffer[i]=new PlayersBuffers();
			this.messageToBeDispatched=null;
		}
	}	

	private void publish(DTOGame dtoGame){
		if(dtoGame.getReceiver()==9) {
			for(int i=0;i<numberOfPlayers;i++) {
				if(i==dtoGame.getPlayerNumber()) continue;
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
