package controller;

import model.*;

public class Turn {

	private Player playerPlay;
	private int numberPlayer;
	private boolean move;
	private boolean attack;
	private boolean drawASectorCard;
	private boolean discard;
	private Game game;
	
	public Turn(int numberOfPlayers, Game game) {
		this.game=game;
		this.playerPlay = game.getPlayers(numberPlayer);	//load the player than has to play
		this.numberPlayer = 0;
		move=false;
		attack=false;
		drawASectorCard=true;
		discard=false;
	}

	public String turn(TurnDTO dataTurn) {
		String response="";
		if(ckeckCard(dataTurn.getTypeCard())==false) {
			response+="You don't have this type of card";
		}
		if(dataTurn.getCoordinate()!=null && game.getMap().isNull(dataTurn.getCoordinate())==false) {		//control if the coordinate are correct
			if(dataTurn.getTypeCard()==ItemCardType.SPOTLIGHT) {	//use spotlight
				response+="";
				//ESEGUI LO SPOTLIGHT CHE RESTITUISCE LA STRINGA CON I GIOCATORI INDIVIDUATI
			}
			else {
				if(move==false) {		//muove
					//METODO PER VERIFICARE CHE SI POSSA MUOVERE DOVE DICE
					//SE SI FALLO MUOVERE E RESTITUISCI LA STRINGA, SETTA IL PARAMETRO MOVE A TRUE, NON PESCARE LA CARTA SETTORE PERICOLOSO
				}
				else {			//complete the effect noise in any sector
					response+="";
				}
			}
		}
		else response+="The sector: "+dataTurn.getCoordinate().getX()+" "+dataTurn.getCoordinate().getY()+" don't exist, please retry";
		if(dataTurn.isAttack()==true&& move==true && playerPlay.getType()==PlayerType.ALIEN) {	//attack only if you have already move and you are alien
			//FAI ATTACCARE AND RESTITUISCI LA STRINGA CON I RISULTATI
		}
		if(dataTurn.getTypeCard()!=null) {
			if(discard==true) {			//discard
				//VERIFICA E FAI SCARTARE, POI NOTIFICA
			}
			else {						//useObject
				if(dataTurn.getTypeCard()==ItemCardType.SEDATIVES) {
					drawASectorCard=false;
					// FAI SCARTARE LA CARTA
				}
				if(dataTurn.getTypeCard()==ItemCardType.TELEPORT) {
					// USA IL TELETRASPORTO E NOTIFICA
				}
				if(move==true) {
					if(dataTurn.getTypeCard()==ItemCardType.ATTACK) {
						//fallo attaccare e riporta come per l'alieno
					}
				}
				else {
					if(dataTurn.getTypeCard()==ItemCardType.ADRENALINE) {
						//USA L'ADRENALINA E NOTIFICA
					}
					
				}
				return "You can't use that card in this moment";
			}
		}
		if(dataTurn.getEndTurn()==true) {
			if(move==false) {
				return "You must move during your turn";
			}
			if(move==true && drawASectorCard==true && attack==false) {
				//FAI PESCARE LA CARTA SETTORE E NOTIFICA
			}
			resetTurn();
		}
		return response;		//eliminalo non deve esistere alla fine
	}

	private void resetTurn() {
		move=false;
		attack=false;
		drawASectorCard=true;
		discard=false;
	}

	private boolean ckeckCard(ItemCardType typeCard) {				//control if the player has this card or this attributes is null
		if(typeCard==null) return true;
		for(int i=0;i<playerPlay.getItem().size();i++) {
			if(playerPlay.getItem().get(i).getType()==typeCard) return true;
		}
		return false;
	}
}
