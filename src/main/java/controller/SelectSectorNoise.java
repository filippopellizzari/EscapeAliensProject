package controller;

public class SelectSectorNoise implements TryToDoAnAction {

	private GameStatus gameStatus;

	public SelectSectorNoise(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}

	@Override
	public String doAction(DTOTurn dtoTurn) {
		if (gameStatus.isMove() && gameStatus.isNoiseInAnySector()
				&& dtoTurn.getCoordinate() != null
				&& dtoTurn.getTypeCard() == null) { // indica il settore del
													// noise in any sector
			gameStatus.setNoiseInAnySector(true);
			return gameStatus.getPlayerPlay() + " : NOISE IN SECTOR "
					+ dtoTurn.getCoordinate() + "\n";
		} else
			return "Non puoi usare in questo momento il Noise in Any Sector";
	}

}
