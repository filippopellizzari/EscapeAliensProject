package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class NoisePlay {

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
