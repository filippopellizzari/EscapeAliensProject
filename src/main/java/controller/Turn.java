package controller;

import model.*;

public class Turn {

	private Player playerPlay;
	private int numberPlayer;
	private int numberOfPlayer;
	private boolean move;						//ha mosso
	private boolean attack;						//ha attaccato
	private boolean drawASectorCard;			//ha pescato la carta settore pericoloso
	private boolean discard;					//deve scartare
	private boolean noiseInAnySector;			//ha pescato la carta noise in any sector
	private Game game;
	
	public Turn(int numberOfPlayers, Game game) {
		this.game=game;
		this.playerPlay = game.getPlayers(0);	//load the player than has to play
		this.numberPlayer = 0;
		this.numberOfPlayer=numberOfPlayers;	//numero di giocatori
		move=false;
		attack=false;
		drawASectorCard=true;
		discard=false;
	}

	public String turn(TurnDTO turnDTO2) {
		String response="";
		if(attack==false&&move==true&&drawASectorCard==false&&turnDTO2.getTypeCard()==ItemCardType.ATTACK&&playerPlay.getPlayerType()==PlayerType.HUMAN);	//attacco
		if(turnDTO2.getTypeCard()==ItemCardType.SPOTLIGHT&&turnDTO2.getCoordinate()!=null);	//spotlight
		if(turnDTO2.getTypeCard()==ItemCardType.SEDATIVES&&turnDTO2.isUseCard()==true);	//sedatives
		if(turnDTO2.getTypeCard()==ItemCardType.ADRENALINE&&turnDTO2.isUseCard()==true);	//adrenaline
		if(turnDTO2.getTypeCard()==ItemCardType.TELEPORT&&turnDTO2.isUseCard()==true);	//teletrasporto
		if(move==false&&turnDTO2.getTypeCard()==null && turnDTO2.getCoordinate()!=null&&turnDTO2.isMove()==true);	//mossa
		if(move==true&&discard==true&&turnDTO2.getTypeCard()!=null&&turnDTO2.isUseCard()==false);	//scarta carta
		if(move==true&&attack==false&&turnDTO2.isAttack()==true&&drawASectorCard==true);	//attacco alieno
		if(move==true&&attack==false&&noiseInAnySector==true&&turnDTO2.isEndTurn()==true&&drawASectorCard==true);   //indica settore per  cartasettore pericoloso
		if(noiseInAnySector==true&&turnDTO2.getCoordinate()!=null&&turnDTO2.getTypeCard()==null);	//indica il settore del noise in any sector
		if(move==true&&discard==false&&noiseInAnySector==false&&discard==false&&turnDTO2.isEndTurn()==true);	//fine turno
		return response;
	}

	private void resetTurn() {
		move=false;
		attack=false;
		drawASectorCard=true;
		discard=false;
		noiseInAnySector=false;
	}

	public String completeTurn() {
		int condizione;					//se arriva a 4 vuol dire che il turno è finito
		String s="";
		do{
			condizione=0;
			if(move==false){ //non ha mosso
				//muovi a caso
			}
			else condizione++;
			if(discard==true) { //non ha scartato
				//scarta
			}
			else condizione++;
			if(drawASectorCard==true) {		
				if(playerPlay.getCurrentSector().getSectorType()==SectorType.DANGEROUS) { //verifica che debba pescare la carta settore pericoloso
					
				}
				else condizione++;
			}
			else condizione++;
			if(noiseInAnySector==true) { //non ha usato il rumore
				//usa il rumore a caso
			}
			else condizione++;
		} while(condizione<=3);
		return s;
	}
	public int getNumberPlayer(){			//number of player
		return numberPlayer%numberOfPlayer;
	}
}
