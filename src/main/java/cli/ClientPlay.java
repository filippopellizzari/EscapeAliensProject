package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is used to analyze the input of client,
 * to do the right action that client wants
 * 
 * @author Filippo
 *
 */
public class ClientPlay {

	private ClientData cd;
	private DTOSend dtoSend;
	private Scanner in;

	/**
	 * 
	 * @param cd
	 *            ClientData, used to send an action to server
	 */
	public ClientPlay(ClientData cd) {
		this.cd = cd;
	}

	/**
	 * 
	 * @param action
	 * @param input
	 * @throws UnknownHostException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void play(int action, Scanner input) throws UnknownHostException,
			ClassNotFoundException, IOException, InterruptedException {

		in = input;
		int azione = action;

		switch (azione) {
		case 1:
			new MovePlay().action(dtoSend, cd, in);
			break;
		case 2:
			new AttackPlay().action(dtoSend, cd);
			break;
		case 3:
			new UseItemPlay().action(dtoSend, cd, in);
			break;
		case 4:
			new DiscardPlay().action(dtoSend, cd, in);
			break;
		case 5:
			new DrawPlay().action(dtoSend, cd);
			break;
		case 6:
			new NoisePlay().action(dtoSend, cd, in);
			break;
		case 7:
			endTurn();
			break;
		case 8:
			chat();
			break;
		default:
			break;
		}

	}

	/**
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void endTurn() throws UnknownHostException, IOException,
			InterruptedException {
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
				ActionType.ENDTURN, null);
		cd.clickOnDoMove(dtoSend);
	}

	/**
	 * 
	 * @throws InterruptedException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void chat() throws InterruptedException, UnknownHostException,
			IOException {
		String mess;

		mess = in.nextLine();
		System.out.println("Scrivi un messaggio di chat:");
		mess = in.nextLine();

		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
				ActionType.CHAT, mess);
		cd.clickOnDoMove(dtoSend);
	}

}
