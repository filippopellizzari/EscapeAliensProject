package gui;

import java.io.IOException;
import java.net.UnknownHostException;

import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is used to discard the ItemCard selected in the JTable, after
 * clicking on Discard button
 * 
 * @author Filippo
 *
 */
public class DiscardAction {

	/**
	 * 
	 * @param cd
	 * @param row
	 *            row selected in the JTable
	 * @throws InterruptedException
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void discard(ClientData cd, int row) throws InterruptedException,
			UnknownHostException, IOException {

		DTOSend dtoSend;

		switch (row) {
		case 0:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ATTACK, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 1:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.TELEPORT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 2:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SEDATIVES, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 3:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.SPOTLIGHT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 4:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.ADRENALINE, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 5:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
					ItemCardType.DEFENSE, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		default:
			break;
		}

	}
}
