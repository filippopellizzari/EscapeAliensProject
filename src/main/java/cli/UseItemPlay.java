package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class UseItemPlay {

	public void action(DTOSend dtoSend, ClientData cd, Scanner in) throws InterruptedException, UnknownHostException,
			IOException {
		System.out
				.println("Scegli tipo:\n 1: ATTACK\n 2: TELEPORT\n 3: SEDATIVES\n 4: SPOTLIGHT\n 5: ADRENALINE");
		int typeToUse;
		do {
			typeToUse = in.nextInt();
		} while (typeToUse < 1 || typeToUse > 5);

		switch (typeToUse) {
		case 1:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ATTACK, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 2:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.TELEPORT, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 3:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SEDATIVES, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 4:

			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SPOTLIGHT, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 5:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ADRENALINE, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		default:
			break;
		}
	}

}
