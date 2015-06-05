package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import connection.*;

public class SocketStart extends SocketBase implements Runnable{
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public SocketStart(ClientData clientData) throws UnknownHostException, IOException {
		super(clientData);
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}
	@Override
	public void run() {
		try {
			System.out.println("Connection Established");
	        out.writeObject(clientData.getToken());
	        out.flush();
			clientData.setToken((Token)in.readObject());	//assegna token
	        in.close();
	        out.close();
        } catch (ClassNotFoundException | IOException e) {
			System.err.println("errore");
		}
	}
}