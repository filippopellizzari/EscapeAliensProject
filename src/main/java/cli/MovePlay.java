package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is used to try a move action
 * 
 * @author Filippo
 *
 */
public class MovePlay {

	/**
	 * 
	 * @param dtoSend
	 * @param cd
	 * @param in
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void action(DTOSend dtoSend, ClientData cd, Scanner in)
			throws UnknownHostException, IOException, InterruptedException {
		
		System.out.println("Inserisci le coordinate:");

		System.out.println("Lettera:");
		char letteraMove = in.next().charAt(0);
		int xMove = (int) letteraMove - 96; // converto char/int

		System.out.println("Numero:");
		int yMove = in.nextInt();

		Coordinate coordMove = new Coordinate(xMove, yMove);
		dtoSend = new DTOSend(coordMove, cd.getView().getNumberPlayer(), null,
				ActionType.MOVE, null);
		cd.clickOnDoMove(dtoSend);

	}

}
