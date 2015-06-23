package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dto.*;
import model.*;

/**
 * This class completes obliged actions if time of player's turn has expired and
 * player has not completed one or more of these actions
 * 
 * @author Filippo
 * @author Nicola
 *
 */

public class CompleteTurn {

	private Turn currentTurn;
	private GameStatus status;
	private DTOGame dtoGame;
	private DTOTurn dtoTurn;
	private final List<DTOGame> dtoGameList;
	private Random random;
	private int condizione;

	/**
	 * 
	 * @param status the status of a turn
	 */

	public CompleteTurn(Turn currentTurn) {
		this.currentTurn = currentTurn;
		this.status = currentTurn.getStatus();
		this.dtoGameList = new ArrayList<DTOGame>();
	}

	/**
	 * 
	 * @param dtoGameList
	 * @return dtoGameList, list of all the actions done
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */

	public List<DTOGame> completeTurn() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		random = new Random();
		// ciclo finchè tutte le 4 azioni obbligatorie
		// non sono state completate a random
		do {
			condizione = 0;
			checkMove();
			checkMustDiscardItem();
			checkMustDraw();
			checkMustNoise();
		} while (condizione < 4);
		for(int i=0;i<dtoGameList.size();i++) {
			if(dtoGameList.get(i).getReceiver()==9) {		//serve per far pervenire l'azione anche a chi la fatta 
				//simulando la comunicazione client server
				DTOGame dtoGameSimulationClientServer=new DTOGame();
				dtoGameSimulationClientServer.setCoordinate(dtoGameList.get(i).getCoordinate()[dtoGameList.get(i).getPlayerNumber()], dtoGameList.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setActionType(dtoGameList.get(i).getActionType());
				dtoGameSimulationClientServer.setGameMessage(dtoGameList.get(i).getGameMessage());
				dtoGameSimulationClientServer.setHatchCardColor(dtoGameList.get(i).getHatchCardColor());
				dtoGameSimulationClientServer.setItemCardType(dtoGameList.get(i).getItemCardType());
				dtoGameSimulationClientServer.setPlayerNumber(dtoGameList.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setReceiver(dtoGameList.get(i).getPlayerNumber());
				dtoGameSimulationClientServer.setSectorCardType(dtoGameList.get(i).getSectorCardType());
				dtoGameList.add(dtoGameSimulationClientServer);
			}
		}
		return dtoGameList;
	}
	
	/**
	 * Moves the player 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	private void checkMove() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if (!status.isMoved()) {
			boolean findSector=false;
			for(int i=0;i<6 && !findSector;i++) {
				Coordinate randomCoord = status.getPlayer().getSector()
					.getAdjacent().get(i);
				if (!status.getGame().getMap().isNull(randomCoord) &&
						!status.getGame().getMap().getSector(randomCoord).isClosed()) {
					if(status.getPlayer().getType()==PlayerType.ALIEN &&
							status.getGame().getMap().getSector(randomCoord).getType()==SectorType.HATCH);
					else {
						dtoTurn = new DTOTurn(randomCoord, null, ActionType.MOVE);
						dtoGame = new DTOGame();
						dtoGame = currentTurn.action(dtoTurn);
						dtoGameList.add(dtoGame);
						findSector=true;
					}
				}
			}
		} else {
			condizione++;
		}
	}
	
	/**
	 * Draws a card from the deck
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	private void checkMustDraw() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if (status.isMustDraw()) {
			dtoTurn = new DTOTurn(null, null, ActionType.DRAWSECTORCARD);
			dtoGame = new DTOGame();
			dtoGame = currentTurn.action(dtoTurn);
			dtoGameList.add(dtoGame);
		} else {
			condizione++;
		}
	}
	
	/**
	 * Discards a item from the players
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	private void checkMustDiscardItem() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if (status.isMustDiscardItem()) {
			ItemCardType randomType = status.getPlayer().getItem()
					.get(random.nextInt(4)).getType();
			dtoTurn = new DTOTurn(null, randomType, ActionType.DISCARDITEM);
			dtoGame = new DTOGame();
			dtoGame = currentTurn.action(dtoTurn);
			dtoGameList.add(dtoGame);
		} else {
			condizione++;
		}

	}
	
	/**
	 * Selects the sector where does the noise
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */

	private void checkMustNoise() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		if (status.isMustNoise()) {
			Coordinate randomCoord = new Coordinate(random.nextInt(22) + 1,
					random.nextInt(13) + 1);
			if (!status.getGame().getMap().isNull(randomCoord)) {
				dtoTurn = new DTOTurn(randomCoord, null,
						ActionType.SELECTSECTORNOISE);
				dtoGame = new DTOGame();
				dtoGame = currentTurn.action(dtoTurn);
				dtoGameList.add(dtoGame);
			}
		} else {
			condizione++;
		}
	}
	
	/**
	 * @return the game status
	 */

	public GameStatus getStatus() {
		return status;
	}

}
