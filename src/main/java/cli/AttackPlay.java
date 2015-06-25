package cli;

import java.io.IOException;
import java.net.UnknownHostException;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is used to try an attack action
 * 
 * @author Filippo
 *
 */
public class AttackPlay {
	/**
	 * 
	 * @param dtoSend
	 * @param cd
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void action(DTOSend dtoSend, ClientData cd)
			throws UnknownHostException, IOException, InterruptedException {
		
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
				ActionType.ATTACK, null);
		cd.clickOnDoMove(dtoSend);
	}
}
