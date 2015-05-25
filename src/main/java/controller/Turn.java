package controller;

import model.*;
/**
 * La classe interpreta le intenzioni del client, analizzando gli oggetti passati dal DTO del turno;
 * controlla che l'azione possa essere eseguita in quel particolare momento del turno: se sì, l'azione
 * viene chiamata, altrimenti viene mandato un messaggio di errore;
 * 
 * @author Nicola
 * @author Filippo
 *
 */
public class Turn {

	private Game model;
	private Player player;
	private boolean hasMoved;			//ha già mosso
	private boolean hasAttacked;		//ha già attaccato
	private boolean drawSectorDuty;	    //obbligo di pescare una carta settore pericoloso
	private boolean discardItemDuty;	//obbligo di scartare una carta oggetto, se ne ha pescate 4 e non vuole o non può giocarne una
	private boolean noiseAnySectorDuty;	//obbligo di scegliere una coordinata per l'effetto NOISE IN ANY SECTOR della carta settore pericoloso
	

	
	public Turn(Game model, int currentPlayer) {
		this.model = model;
		this.player = model.getPlayers(currentPlayer);	//load the player that has to play
		this.hasMoved = false;
		this.hasAttacked = false;
		this.drawSectorDuty = false;
		this.discardItemDuty = false;
		this.noiseAnySectorDuty = false;
	}

	
	public String turn(DTOTurn DTOTurn) {
		
		String response = "";
		
		/**
		 * player wants to use an Item Card (no attack type); he can do it in every moment of the turn
		 */
		
		if(DTOTurn.getItemCardType().equals(ItemCardType.TELEPORT) && DTOTurn.wantsToUseItem()){	
			return new ItemAction(model, player).teleport();
		}
		if(DTOTurn.getItemCardType().equals(ItemCardType.SEDATIVES) && DTOTurn.wantsToUseItem()){	
			return new ItemAction(model, player).sedatives();
		}	
		if(DTOTurn.getItemCardType().equals(ItemCardType.SPOTLIGHT) && DTOTurn.getCoordinate()!=null && DTOTurn.wantsToUseItem()){	
			return new ItemAction(model,player).spotlight(DTOTurn.getCoordinate());
		}
		if(DTOTurn.getItemCardType().equals(ItemCardType.ADRENALINE) && DTOTurn.wantsToUseItem()){
			return new ItemAction(model, player).adrenaline();
		}	
		
		/**
		 * a human wants to attack
		 */
		if(DTOTurn.getItemCardType().equals(ItemCardType.ATTACK) && !hasAttacked && hasMoved && !drawSectorDuty){
			hasAttacked = true;
			return new ItemAction(model, player).attack();
		}
		/**
		 * an alien wants to attack
		 */
		if(hasMoved && !hasAttacked && DTOTurn.isAttack() && drawSectorDuty){	
			return new Attack(model, player).attackMove();
		}
		
		/**
		 * player decides to move
		 */
		if(!hasMoved && DTOTurn.getItemCardType()==null && DTOTurn.getCoordinate()!=null && DTOTurn.isMove()){	//mossa
			if(new MoveRules(model, player).moveCheck(DTOTurn.getCoordinate())) {
				hasMoved = true;
				response += new MoveAction(model, player).move(DTOTurn.getCoordinate());
				if(player.getSector().getType() != SectorType.DANGEROUS){
					drawSectorDuty = true;	//se non finisci in un settore pericoloso, non hai l'obbligo di pescare una carta settore pericoloso
				}
				return response;
			}
			else
				return "Non puoi muovere in quel settore";
		}
		
		/**
		 * player decides to draw a SectorDangerousCard
		 */
		if(hasMoved && !hasAttacked && noiseAnySectorDuty && DTOTurn.isEndTurn() && !drawSectorDuty ){   //pesca carta settore pericoloso
			drawSectorDuty = true;
			response += new CardsEffect(model,player).drawSectorCard();
			if(response.substring(0,11).equals("NOISE IN ANY")){
				noiseAnySectorDuty = true;		//controlla se è un noise any
			}
			if(player.getItem().size()==4) {
				discardItemDuty = true;
			}
			return response;
		}
		/**
		 * player decides to discard an Item Card, but not to use it
		 */
		if(hasMoved && discardItemDuty && DTOTurn.getItemCardType()!=null && !DTOTurn.wantsToUseItem()){
			discardItemDuty = false;
			return new CardsEffect(model, player).discardItemDuty(DTOTurn.getItemCardType());
		}
		/**
		 * player must choose one "bluff" coordinate
		 */
		if(hasMoved && noiseAnySectorDuty && DTOTurn.getCoordinate()!=null && DTOTurn.getItemCardType()==null) {	
			noiseAnySectorDuty = false;
			return player +" : NOISE IN SECTOR "+DTOTurn.getCoordinate();
		}
		
		/**
		 * player wants to end turn
		 */
		if(hasMoved && !noiseAnySectorDuty && !discardItemDuty && drawSectorDuty && DTOTurn.isEndTurn()) {	
			resetTurn();
			return "Hai finito il turno\n";	
		}
		
		/**
		 * player makes an action in a wrong moment
		 */
		return "Mossa non valida!\n";
	}

	
	private void resetTurn() {
		this.hasMoved = false;
		this.hasAttacked = false;
		this.drawSectorDuty = false;
		this.discardItemDuty = false;
		this.noiseAnySectorDuty = false;
	}

	public String completeTurn() {
		int condizione;					//se arriva a 4 vuol dire che il turno è finito
		String s="";
		do{
			condizione=0;
			if(!hasMoved){ //non ha mosso
				//muovi a caso
			}
			else condizione++;
			if(discardItemDuty) { //non ha scartato
				//scarta
			}
			else condizione++;
			if(!drawSectorDuty) {		
				if(player.getSector().getType().equals(SectorType.DANGEROUS)) { //verifica che debba pescare la carta settore pericoloso
					
				}
				else condizione++;
			}
			else condizione++;
			if(noiseAnySectorDuty) { //non ha usato il rumore
				//usa il rumore a caso
			}
			else condizione++;
		} while(condizione<=3);
		return s;
	}
}
