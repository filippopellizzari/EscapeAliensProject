package controller;

import dto.DTOTurn;
import model.*;

public class DrawSectorCard implements TryToDoAnAction {

	private GameStatus gameStatus;

	public DrawSectorCard(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	public String drawSectorCard() {
		String s = "";
		Game model = gameStatus.getGame();
		Player player = gameStatus.getPlayerPlay();
		SectorCard current = model.getSectorCards().draw();
		SectorCardType type = current.getType();
		switch (type) {
		case NOISEANY:
			s += "NOISE IN ANY SECTOR: scegli una coordinata\n"; // messaggio
																	// privato
			return s;

		case NOISEYOUR:
			s += player + " : NOISE IN SECTOR "
					+ player.getSector().getCoordinate() + "\n"; // messaggio
																	// pubblico
			break;

		case SILENCE:
			s += player + " : SILENCE IN ALL SECTORS\n"; // messaggio pubblico
			break;
		default:
			break;

		}
		if (current.isItemIcon()) {
			s += "Devi pescare una carta oggetto\n";// messaggio privato
			s += new DrawItemCard(model, player).drawItemCard();
		}
		model.getSectorCards().discard(current);
		return s;

	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (!gameStatus.hasMoved() && gameStatus.hasAttacked()
				&& gameStatus.isNoiseInAnySector()
				&& !gameStatus.isSolveSectorDuty()
				&& !gameStatus.getPlayerPlay().isSedated()) { // pesca carta
																// settore
																// pericoloso
			gameStatus.setSolveSectorDuty(true);
			String response = drawSectorCard();
			if (response.substring(0, 11) == "NOISE IN ANY")
				gameStatus.setNoiseInAnySector(true); // controlla se è un noise
			if (gameStatus.getPlayerPlay().getItem().size() == 4)
				gameStatus.setDiscardItemDuty(true);
			return response;
		} else
			return "Non puoi pescare in questo momento";
	}

}
