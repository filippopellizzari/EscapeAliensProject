package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is used to try an action of selection coordinates for noise in any
 * sector
 * 
 * @author Filippo
 *
 */
public class NoisePlay {

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
		int xSelect = (int) letteraMove - 96; // converto char/int

		System.out.println("Numero:");
		int ySelect = in.nextInt();

		Coordinate coordSelect = new Coordinate(xSelect, ySelect);
		dtoSend = new DTOSend(coordSelect, cd.getView().getNumberPlayer(),
				null, ActionType.SELECTSECTORNOISE, null);
		cd.clickOnDoMove(dtoSend);
	}
}
