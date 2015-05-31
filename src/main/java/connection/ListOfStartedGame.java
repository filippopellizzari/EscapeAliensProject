package connection;

import java.util.List;

public class ListOfStartedGame {
	private List<GameDescription> gameDescriptionList;

	/**
	 * @return the gameDescriptionList
	 */
	public List<GameDescription> getGameDescriptionList() {
		return gameDescriptionList;
	}

	/**
	 * @param gameDescriptionList the gameDescriptionList to set
	 */
	public void setGameDescriptionList(List<GameDescription> gameDescriptionList) {
		this.gameDescriptionList = gameDescriptionList;
	}
	public GameDescription getNumberGameDescription(int number) {
		return gameDescriptionList.get(number);
	}
}
