package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class DiscardPlay {

	public void action(DTOSend dtoSend, ClientData cd, Scanner in)
			throws UnknownHostException, IOException, InterruptedException {
		System.out
				.println("Scegli tipo:\n 1: ATTACK\n 2: TELEPORT\n 3: SEDATIVES\n 4: SPOTLIGHT\n 5: ADRENALINE");

		int typeToDiscard;
		do {
			typeToDiscard = in.nextInt();
		} while (typeToDiscard < 1 || typeToDiscard > 5);

		switch (typeToDiscard) {
		case 1:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ATTACK, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 2:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.TELEPORT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 3:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SEDATIVES, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 4:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SPOTLIGHT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 5:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ADRENALINE, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		default:
			break;
		}
	}

}
