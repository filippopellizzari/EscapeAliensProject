package cli;

import model.Coordinate;
import model.PlayerType;
import dto.DTOGame;

public class UseItemMessage implements Message{

	@Override
	public void receive(DTOGame dtoGame){
		System.out.println("<giocatore " + (dtoGame.getPlayerNumber()+1)
				+ ">" + " ha usato la carta "
				+ dtoGame.getItemCardType());
		switch (dtoGame.getItemCardType()) {
		case ATTACK:
			System.out
					.println("<giocatore "
							+ (dtoGame.getPlayerNumber()+1)
							+ ">"
							+ " ATTACCO IN SETTORE "
							+ dtoGame.getCoordinate(dtoGame
									.getPlayerNumber()));
			for (int i = 0; i < dtoGame.getPlayerType().length; i++) {
				PlayerType type = dtoGame.getPlayerType(i);
				if (type != null) {
					System.out.println("<giocatore " + (i+1) + ">"
							+ " è stato attaccato e viene eliminato:\n"
							+ "era un " + type);
				}
			}
			if (dtoGame.getGameMessage() != null) {
				System.out.println(dtoGame.getGameMessage());
			}
			break;
		case SPOTLIGHT:
			for (int i = 0; i < dtoGame.getCoordinate().length; i++) {
				Coordinate coord = dtoGame.getCoordinate(i);
				if (coord != null) {
					System.out.println("<giocatore " + (i+1) + ">"
							+ " si trova nel settore" + coord);
				}
			}
			break;
		default:
			break;
		}
	}
}
