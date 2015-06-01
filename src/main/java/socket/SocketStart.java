package socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import connection.Token;
import connection.TypeOfMap;

public class SocketStart extends SocketBase{
	
	private List<TypeOfMap> typeOfMap;
	public SocketStart(int port, String host, Token token,
			List<TypeOfMap> typeOfMap) throws UnknownHostException, IOException {
		super(port, host, token);
		this.typeOfMap=typeOfMap;
	}

	@Override
	public void startClient() throws IOException, ClassNotFoundException {
		while (true) {
			outputStream.writeObject(token);	// sends the token to the server
			outputStream.flush();
			token=(Token) inputStream.readObject();	//save the token passed by server
			TypeOfMap typeOnInput;
			typeOnInput=(TypeOfMap)inputStream.readObject();
			while(typeOnInput!=null); {
				typeOfMap.add(typeOnInput);		//receive game mapName					//save the game in List
				typeOnInput=(TypeOfMap)inputStream.readObject();
			} 
			inputStream.close();	//close all the resource
			outputStream.close();
			socket.close();
		}
	}

}
