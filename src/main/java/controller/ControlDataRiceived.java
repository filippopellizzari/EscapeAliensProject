package controller;

import model.Game;
import model.PlayerType;

public class ControlDataRiceived {

	public String verify(DTOSend dtoSend, int numberPlayer, Game game) {
		if(numberPlayer!=dtoSend.getNumberPlayer()) return "Ora non è il tuo turno";
		if(game.getPlayers(numberPlayer).isAlive()==false) return "Sei morto e i morti non possono agire";
		else {
			if(dtoSend.getCoordinate()!=null){			//controllo coordinate
				if(game.getMap().isNull(dtoSend.getCoordinate())) return "Le coordinate sono errate";
			}
			if(dtoSend.getTypeCard()!=null) {			//controllo carta
				boolean findCard=false;
				for(int i=0;i<game.getPlayers(numberPlayer).getItem().size();i++)
					if(game.getPlayers(numberPlayer).getItem().get(i).getType()==dtoSend.getTypeCard()) findCard=true;	//ho trovato la carta
				if(findCard==false) 
					return "Non possiedi questa carta";
			}
			return "OK";
		}
	}
}
