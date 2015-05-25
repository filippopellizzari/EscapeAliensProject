package controller;

import model.Game;
import model.PlayerType;

public class ControlDataReceived {

	public String verify(DTOSend dtoSend, int numberPlayer, Game model) {
		if(numberPlayer != dtoSend.getNumberPlayer()){
			return "Ora non è il tuo turno";
		}
		
		if(!model.getPlayers(numberPlayer).isAlive()){
			return "Sei morto e i morti non possono agire!";
		}
		
		if(dtoSend.getCoordinate() != null){			//controllo coordinate
			if(model.getMap().isNull(dtoSend.getCoordinate())){
				return "Le coordinate sono errate";
			}
		}
		
		if(dtoSend.getItemCardType() != null) {			//controllo se ha carta oggetto
			boolean findCard = false;
			for(int i = 0; i < model.getPlayers(numberPlayer).getItem().size(); i++){
				if(model.getPlayers(numberPlayer).getItem().get(i).getType().equals(dtoSend.getItemCardType())){
					findCard = true;	//ho trovato la carta
				}
			}
			if(!findCard) 
				return "Non possiedi questa carta";
		}
		
		if(dtoSend.getItemCardType() != null && model.getPlayers(numberPlayer).getType().equals(PlayerType.ALIEN) 
				&& dtoSend.wantsToUseItem()) {	
			return "Gli alieni non possono usare le carte";
		}
		
		return "OK";
		
	}
}
