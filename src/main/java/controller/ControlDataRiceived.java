package controller;

import model.Game;

/**
 * In this class there are some controls: 1. the player who send the message is
 * the player that has to do his turn; 2. control of his status (dead or alive);
 * 3. control about the right coordinates send by player; 4. control about the
 * possession of an item card.
 * 
 * @author Nicola
 *
 */

public class ControlDataRiceived {

	/**
	 * 
	 * @param dtoSend
	 *            , the object send from client
	 * @param numberPlayer
	 *            , number of player that has to play
	 * @param game
	 *            , a reference at model
	 * @return string, this explain if the date are correct returning "OK" or
	 *         incorrect
	 */

	public String verify(DTOSend dtoSend, int numberPlayer, Game game) {
		if (numberPlayer != dtoSend.getNumberPlayer())
			return "Ora non è il tuo turno";
		if (game.getPlayers(numberPlayer).isAlive() == false)
			return "Sei morto e i morti non possono agire";

		if (dtoSend.getCoordinate() != null) {
			if (game.getMap().isNull(dtoSend.getCoordinate()))
				return "Le coordinate sono errate";
		}
		if (dtoSend.getTypeCard() != null) {
			boolean findCard = false;
			for (int i = 0; i < game.getPlayers(numberPlayer).getItem().size(); i++){
				if (game.getPlayers(numberPlayer).getItem().get(i).getType()
						.equals(dtoSend.getTypeCard())){
					findCard = true;
				}
			}
			if (!findCard){
				return "Non possiedi questa carta";
			}
			
		}
		
		return "OK";

	}
}
