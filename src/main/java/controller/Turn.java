package controller;

import model.*;

public class Turn {

	private Player playerPlay;
	private boolean move;						//ha mosso
	private boolean attack;						//ha attaccato
	private boolean solveSectorDuty;			//ha pescato la carta settore pericoloso
	private boolean discardItemDuty;					//deve scartare
	private boolean noiseInAnySector;			//ha pescato la carta noise in any sector
	private Game game;
	
	public Turn(Game game, int currentPlayer) {
		this.game=game;
		this.playerPlay = game.getPlayers(currentPlayer);	//load the player than has to play
		move=false;
		attack=false;
		solveSectorDuty=false;
		discardItemDuty=false;
		noiseInAnySector=false;
	}

	public String turn(TurnDTO turnDTO2) {
		String response="";
		Attack attackAlien;
		UseItem useItem;
		MoveRules moveRules;
		MoveAction moveAction;
		if(attack==false&&move==true&&solveSectorDuty==false&&turnDTO2.getTypeCard()==ItemCardType.ATTACK);	//attacco
		{
			attack=true;
			useItem=new UseItem(game,playerPlay);
			response=useItem.attack();
		}
		if(turnDTO2.getTypeCard()==ItemCardType.SPOTLIGHT&&turnDTO2.getCoordinate()!=null&&turnDTO2.isUseCard()==true) {	//spotlight
			useItem=new UseItem(game,playerPlay);
			response=useItem.spotlight(game.getMap().getSector(turnDTO2.getCoordinate()));
		}
		if(turnDTO2.getTypeCard()==ItemCardType.SEDATIVES&&turnDTO2.isUseCard()==true){	//sedatives
			useItem=new UseItem(game,playerPlay);
			response=useItem.sedatives();
		}
		if(turnDTO2.getTypeCard()==ItemCardType.ADRENALINE&&turnDTO2.isUseCard()==true){	//adrenaline
			useItem=new UseItem(game,playerPlay);
			response=useItem.adrenaline();
		}
		if(turnDTO2.getTypeCard()==ItemCardType.TELEPORT&&turnDTO2.isUseCard()==true){	//teletrasporto
			useItem=new UseItem(game,playerPlay);
			response=useItem.teleport();
		}
		if(move==false&&turnDTO2.getTypeCard()==null && turnDTO2.getCoordinate()!=null&&turnDTO2.isMove()==true){	//mossa
			moveRules=new MoveRules(game, playerPlay);
			if(moveRules.moveCheck(turnDTO2.getCoordinate())) {
				move=true;
				moveAction=new MoveAction(game, playerPlay);
				moveAction.move(turnDTO2.getCoordinate());
				if(playerPlay.getSector().getType()!=SectorType.DANGEROUS) solveSectorDuty=true;	//se non sei in set pericolo non devi pescare
			}
			else
				return "Non puoi muovere in quel settore";
		}
		if(move==true&&discardItemDuty==true&&turnDTO2.getTypeCard()!=null&&turnDTO2.isUseCard()==false){	//scarta carta
			//discard card
			//discard=false;
		}
		if(move==true&&attack==false&&turnDTO2.isAttack()==true&&solveSectorDuty==true){	//attacco alieno
			attackAlien=new Attack(game, playerPlay);
			response=attackAlien.attackMove();
		}
		if(move==true&&attack==false&&noiseInAnySector==true&&turnDTO2.isEndTurn()==true&&solveSectorDuty==false){   //pesca carta settore pericoloso
			solveSectorDuty=true;
			Draw draw=new Draw(game,playerPlay);
			response=draw.drawSectorCard();
			if(response.substring(0,11)=="NOISE IN ANY") noiseInAnySector=true;		//controlla se è un noise
			if(playerPlay.getItem().size()==4) discardItemDuty=true;
		}
		if(move==true&&noiseInAnySector==true&&turnDTO2.getCoordinate()!=null&&turnDTO2.getTypeCard()==null) {	//indica il settore del noise in any sector
			noiseInAnySector=false;
			response+=playerPlay +" : NOISE IN SECTOR "+turnDTO2.getCoordinate()+"\n";
		}
		if(move==true&&noiseInAnySector==false&&discardItemDuty==false&&solveSectorDuty==true&&turnDTO2.isEndTurn()==true) {	//fine turno
			resetTurn();
			response+="Hai finito il turno";	
		}
		return response;
	}

	private void resetTurn() {
		move=false;
		attack=false;
		solveSectorDuty=false;
		discardItemDuty=false;
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
			if(discardItemDuty==true) { //non ha scartato
				//scarta
			}
			else condizione++;
			if(solveSectorDuty==false) {		
				if(playerPlay.getSector().getType()==SectorType.DANGEROUS) { //verifica che debba pescare la carta settore pericoloso
					
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
}
