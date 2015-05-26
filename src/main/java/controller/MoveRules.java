package controller;

import model.*;

/**
 * La classe contiene le regole per lo spostamento all'interno della mappa
 * @author Filippo
 *
 */
public class MoveRules implements TryToDoAnAction{
	
	private GameStatus gameStatus;
	public MoveRules(GameStatus gameStatus) {
		this.gameStatus=gameStatus;
	}

	/**
	 * controlla che la mossa sia valida
	 * @param dest
	 * @return
	 */

	public boolean moveCheck(Coordinate dest){

		return pathCheck(gameStatus.getPlayerPlay().getSector().getCoordinate(), dest, gameStatus.getPlayerPlay().getSpeed())
				&& destCheck (dest);		
	}

	/**
	 * controlla che la destinazione sia dentro la mappa; 
	 * nel caso degli alieni, controlla che non possano accedere ad un settore scialuppa
	 * @param dest
	 * @return
	 */
	
	private boolean destCheck(Coordinate dest){
		if(!gameStatus.getGame().getMap().isNull(dest)){
			if(gameStatus.getPlayerPlay().getType()==PlayerType.ALIEN 
			   && gameStatus.getGame().getMap().getSector(dest).getType().equals(SectorType.HATCH)) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	/**
	 * controlla che la destinazione sia raggiungibile secondo la velocità del giocatore
	 *  e che i settori lungo il percorso siano tutti attraversabili
	 * @param curr, coordinata di partenza
	 * @param dest, coordinata di destinazione
	 * @param speed, velocità del giocatore, ossia di quanti settori può spostarsi
	 * @return
	 */
	
	public boolean pathCheck(Coordinate curr,Coordinate dest, int speed){
			if (speed==0){
					return curr.equals(dest);
			}
			else{
				Sector currSector = gameStatus.getGame().getMap().getSector(curr);
				for(int i = 0; i < currSector.getAdjacent().size(); i++){
					Coordinate adjCoord = currSector.getAdjacent().get(i);
					
					if (adjCoord.getX() != -1 && adjCoord.getY() != -1){
						Sector adjSector = gameStatus.getGame().getMap().getSector(adjCoord);
						if(!adjSector.isClosed()){
							speed--;
							if (pathCheck(adjCoord, dest, speed)){
								return true;
							}
							speed++;
						}
							
					}
				}
				}
			return false;		
	}
	
	@Override
	public String doAction(DTOTurn dtoTurn) {
		if(gameStatus.isMove()==false&&dtoTurn.getTypeCard()==null && dtoTurn.getCoordinate()!=null){	//mossa
			if(moveCheck(dtoTurn.getCoordinate())) {
				gameStatus.setMove(true);
				String response=move(dtoTurn.getCoordinate());
				if(gameStatus.getPlayerPlay().getSector().getType()!=SectorType.DANGEROUS) 
					gameStatus.setSolveSectorDuty(true);	//se non sei in set pericolo non devi pescare
				return response;
			}
			else
				return "Non puoi muovere in quel settore";
		}
		else return "Non puoi muovere adesso";
	}
	
	public String move(Coordinate destCoord){
		Sector destSector = gameStatus.getGame().getMap().getSector(destCoord);
		destSector.addPlayer(gameStatus.getPlayerPlay().getSector().removePlayer());
		gameStatus.getPlayerPlay().setSector(destSector);
		String s = "Ti sei spostato nel settore "+destCoord+"\n"; //PRIVATO
		switch(destSector.getType()){
			case DANGEROUS :
				s += "Sei finito su un settore pericoloso!\n"; //PRIVATO
				s += "Puoi decidere se pescare una carta settore pericoloso, attaccare o giocare carta oggetto\n"; //PRIVATO
			case HATCH : 
				s += "Sei finito su un settore hatch!\n"; //PRIVATO
				s += gameStatus.getPlayerPlay()+ " si trova nel settore "+destSector+"\n"; //PUBBLICO
				s+= new CardsEffect(gameStatus.getGame(), gameStatus.getPlayerPlay()).drawHatchCard();
			case SECURE :
				s += "Sei finito su un settore sicuro!\n";
		default:
			break;
		}
		return s;
	}
}
