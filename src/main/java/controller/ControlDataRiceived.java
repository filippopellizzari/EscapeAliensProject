package controller;

import model.Game;
import model.PlayerType;

/**
 * In this class there some control, the fist control that the player who send the message is the player that has to do his turn,
 * after there is control of his status (dead or alive), then a control about the right date send by player
 * @author Nicola
 *
 */

public class ControlDataRiceived {
	
	/**
	 * 
	 * @param dtoSend, the object send from client
	 * @param numberPlayer, number of player that has to play
	 * @param game, a reference at model
	 * @return string, this explain if the date are correct returning "OK" or incorrect
	 */

	public String verify(DTOSend dtoSend, int numberPlayer, Game game) {
		if(numberPlayer!=dtoSend.getNumberPlayer()) 
			return "Ora non è il tuo turno";
		if(game.getPlayers(numberPlayer).isAlive()==false) 
			return "Sei morto e i morti non possono agire";
		else {
			if(dtoSend.getCoordinate()!=null){			//controllo coordinate
				if(game.getMap().isNull(dtoSend.getCoordinate())) 
					return "Le coordinate sono errate";
			}
			if(dtoSend.getTypeCard()!=null) {			//controllo carta
				boolean findCard=false;
				for(int i=0;i<game.getPlayers(numberPlayer).getItem().size();i++)
					if(game.getPlayers(numberPlayer).getItem().get(i).getType()==dtoSend.getTypeCard()) 
						findCard=true;	//ho trovato la carta
				if(findCard==false) 
					return "Non possiedi questa carta";
			}
			return "OK";
		}
	}
}
