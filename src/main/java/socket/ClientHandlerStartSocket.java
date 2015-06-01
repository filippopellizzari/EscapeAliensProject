package socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import connection.*;

public class ClientHandlerStartSocket extends SocketHandler implements Runnable{
	
	private IdentifyTypeOfConnection identifyConnection;
	private Token token;
	private List<TypeOfMap> mapName;
	
	public ClientHandlerStartSocket() throws UnknownHostException, IOException {
		super();
		GameAvailable gameAvailable=new GameAvailable();
		mapName=gameAvailable.getMapName();		//get the type of map
	}
	
	@Override
	public void run() {
		try {
			token=(Token)inputStream.readObject();
			Identification identification=new Identification(identifyConnection.getSize(),token.getTypeConnection(),StatusClient.CHOOSEGAME,0);
			token=new Token(identification.getNumber(),identification.getTipeOfConnection(),token.getName());
			outputStream.writeObject(token);		//send the new token
			for(int i=0;i<mapName.size();i++) {	//send the maps
				outputStream.writeObject(mapName.get(i));
				outputStream.flush();
			}
			outputStream.writeObject(null);		//finita la send
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}	
}