package socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import connection.*;

public class ClientHandlerStartSocket implements Processing{
	
	private IdentifyTypeOfConnection identifyConnection;
	private Token token;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	public ClientHandlerStartSocket(Token token, ObjectOutputStream socketOut, ObjectInputStream socketIn) {
		identifyConnection=IdentifyTypeOfConnection.getinstance();
		this.token=token;
		this.out=socketOut;
		this.in=socketIn;
	}

	public void start() {
		try {
			boolean numberFound=false;
			int i=0;
			Identification identificationToBeWrite;
			do{
				identificationToBeWrite=identifyConnection.getIdentification(i);
				if(identificationToBeWrite==null) {
					identificationToBeWrite=new Identification(i,-1,0);
					identifyConnection.setIdentificationList(identificationToBeWrite, i);  //aggiorna il database
					token=new Token(i);
					out.writeObject(token);		//send the new token
					numberFound=true;		//trovata posizione
				}
				i++;
			}while(numberFound==false&&i<10000);
		} catch (IOException e) {
			System.err.println("ImpallatoClientStartSocket");
		}
	}	
}