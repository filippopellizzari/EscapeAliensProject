package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.UnknownHostException;

import connection.*;

public class SocketStart extends SocketBase implements Runnable{
	
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Token token;
	
	public SocketStart(Token token) throws UnknownHostException, IOException {
		super();
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
			this.token=token;
		} catch (IOException e) {
			System.out.print("Errore");
		}
	}
	@Override
	public void run() {
		try {
			System.out.println("Connection Established");
	        out.writeObject(token);
	        out.flush();
			token = (Token)in.readObject();
			System.out.println(token.getNumber());
	        in.close();
	        out.close();
        } catch (ClassNotFoundException | IOException e) {
			System.err.println("errore");
		}
	}
	public static void main(String[] args) {
		try {
			Token token=new Token(-1);
			Thread inizio=new Thread(new SocketStart(token));
			inizio.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}