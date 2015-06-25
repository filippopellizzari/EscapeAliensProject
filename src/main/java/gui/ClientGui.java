package gui;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import cli.ChooseConnection;
import cli.ChooseMap;
import connection.ClientData;

public class ClientGui {

	private ClientData cd;
	private Scanner in;
	private String mapName;

	public void startClientGui() throws UnknownHostException,
			ClassNotFoundException, NotBoundException, AlreadyBoundException,
			IOException, InterruptedException {

		in = new Scanner(System.in);
		
		cd = new ChooseConnection().choose(cd, in);// scegli RMI o Socket

		// ciclo finchè una partita non è disponibile
		String resultChooseMap;
		
		do {
			mapName = new ChooseMap().choose(cd, in);
			System.out.println("Attendi partita disponibile...");
			resultChooseMap = cd.getBuffer();
			System.out.println(resultChooseMap);
		} while (resultChooseMap
				.contains("Tempo Scaduto e 1 solo giocatore, partita annullata"));

		// da qui la partita è creata
		System.out.println("Numero giocatore: "
				+ (cd.getView().getNumberPlayer() + 1));
		System.out.println("Tipo giocatore: " + cd.getView().getPlayerType());
		System.out.println("Settore corrente: " + cd.getView().getCoordinate());
		
		in.close();
		
		// da qui si crea la grafica 
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Gui gui = new Gui();
				gui.createAndShowGUI(mapName, cd);
				
				RightPanel rp = gui.getGameTable().getRightPanel();
				LeftPanel lp = gui.getGameTable().getLeftPanel();
				DefaultTableModel dataModel = lp.getDiscardPanel().getDataModel();
				
				ClientModelGui model = new ClientModelGui(dataModel);
				try {
					model.setCoordinate(cd.getView().getCoordinate());
				} catch (InterruptedException e) {
					System.out.println("Problem Get Starting Coordinates");
				}
					
				//posizione a inizio partita
				rp.getMyPositionPanel().getTextArea().append(model.getCoordinate().toString()+"\n");
				
				//info per tutta la partita
				try {
					lp.getSouthWestPanel().getTextArea().append("Info Giocatore\n"
							+ "Numero: "+ (cd.getView().getNumberPlayer() + 1)+"\n");
				} catch (InterruptedException e1) {
					System.out.println("Problem Get View: "+e1);
				}
				try {
					lp.getSouthWestPanel().getTextArea().append("Tipo: " + cd.getView().getPlayerType());
				} catch (InterruptedException e1) {
					System.out.println("Problem Get View: "+e1);
					
				}
				
				//primo turno
				rp.getMessagePanel().getTextArea().append("Round 1\n Turno Giocatore 1\n");
				
				//thread dei messaggi
				Thread showMessage = null;
				try {
					showMessage = new Thread(new ShowMessageGui(cd,model,rp));
				} catch (InterruptedException e) {
					System.out.println("Problem Show Message Thread: "+e);
				}
				showMessage.start();

				
			}

		});
		
		

	}

	public static void main(String[] args) throws UnknownHostException,
			ClassNotFoundException, NotBoundException, AlreadyBoundException,
			IOException, InterruptedException {
		ClientGui client = new ClientGui();
		client.startClientGui();
	}

}
