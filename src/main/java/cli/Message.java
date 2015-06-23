package cli;

import dto.DTOGame;

public interface Message {

	public void receive(DTOGame dtoGame);
}
