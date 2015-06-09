package pubSub;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class Broker implements Runnable{

	private final int portNumber = 37777;
	private boolean listening = true;
	private ArrayList<BrokerThread> subscribers = new ArrayList<BrokerThread>();
	private String topic;
	
	@Override
	public void run() {
		try(ServerSocket brokerSocket = new ServerSocket(portNumber)){
			while(listening){
				BrokerThread brokerThread = new BrokerThread(brokerSocket.accept());
				brokerThread.start();
				System.out.println("Adding new subscriber");
				subscribers.add(brokerThread);
			}
		}catch(IOException e){
			System.err.println("Cannot listen on port: "+portNumber);
			System.exit(-1);
		}
	}
	
	public Broker(String topic) {
		this.topic=topic;
	}
	
	public static void main(String[] args) {
		System.out.println("Starting the Broker Service");
		Broker broker = new Broker("Game 1");
		broker.run();
		System.out.println("Write something to publish on topic "+broker.topic+": ");
		Scanner stdin = new Scanner(System.in);
			while (true) {
				String inputLine = stdin.nextLine();
				broker.publish(inputLine);
			}
		
	}
	
	private void publish(String msg){
		if(!subscribers.isEmpty()){
			System.out.println("Publishing message");
			for (BrokerThread sub : subscribers) {
				sub.dispatchMessage(msg);
			}
		}else{
			System.err.println("No subscribers!!");
		}
	}
}
