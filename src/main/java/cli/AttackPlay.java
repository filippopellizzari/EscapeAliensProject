package cli;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class AttackPlay {

	public void action(DTOSend dtoSend, ClientData cd)
			throws UnknownHostException, IOException, InterruptedException {
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
				ActionType.ATTACK, null);
		cd.clickOnDoMove(dtoSend);
	}
}
