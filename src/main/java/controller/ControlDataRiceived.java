package controller;

import model.Game;

public class ControlDataRiceived {

	public String verify(DTOSend dtoSend, int numberPlayer, Game game) {
		if(numberPlayer!=dtoSend.getNumberPlayer()) return "Ora non è il tuo turno";
		else {
			if(dtoSend.getCoordinate()!=null){			//controllo coordinate
				if(game.getMap().isNull(dtoSend.getCoordinate())) return "Le coordinate sono errate";
			}
			if(dtoSend.getTypeCard()!=null) {			//controllo carta
				for(int i=0;i<game.getPlayers(numberPlayer).getItemCardPlayer().size();i++)
				{
					if(game.getPlayers(numberPlayer).getItemCardPlayer().get(i).getItemCardType()==dtoSend.getTypeCard())
						return "OK";
				}
				return "Non possiedi questa carta";
			}
			if(dtoSend.getEndTurn()==true) return "OK";	//controllo fine turno
			return "Non puoi fare questa azione in questo momento";	
		}
	}
}
