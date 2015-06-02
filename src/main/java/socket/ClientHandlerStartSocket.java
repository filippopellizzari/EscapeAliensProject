package socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import connection.*;

public class ClientHandlerStartSocket extends SocketHandler implements Runnable{
	
	private IdentifyTypeOfConnection identifyConnection;
	private GameAvailable gameAvailable;
	private List<TypeOfMap> mapName;
	
	public ClientHandlerStartSocket(Token token) throws UnknownHostException, IOException {
		super(token);
		gameAvailable=GameAvailable.getinstance();
		identifyConnection=IdentifyTypeOfConnection.getinstance();
		mapName=gameAvailable.getMapName();		//get the type of map
	}
	
	@Override
	public void run() {
		try {
			token=(Token)inputStream.readObject();
			boolean numberFound=false;
			int i=0;
			Identification identificationToBeWrite;
			do{
				identificationToBeWrite=identifyConnection.getIdentification(i);
				if(identificationToBeWrite==null) {
					identificationToBeWrite=new Identification(i,token.getTypeConnection(),StatusClient.LOADINGMAPS,0,0);
					token=new Token(i,identificationToBeWrite.getTipeOfConnection(),token.getName());
				}
				outputStream.writeObject(token);		//send the new token
			}while(numberFound==false&&i<10000);
			for(int j=0;j<mapName.size();j++) {		//send the maps
				outputStream.writeObject(mapName.get(j));
				outputStream.flush();
			}
			identifyConnection.getIdentification(token.getNumber()).setStatusClient(StatusClient.CHOOSEGAME);
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