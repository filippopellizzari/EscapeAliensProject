package cli;

import java.util.ArrayList;
import java.util.List;

import model.Coordinate;
import model.ItemCardType;

/**
 * This class represents the model of data which is useful to display to client
 * 
 * @author Filippo
 *
 */
public class ClientModel {

	private Coordinate coordinate;
	private final List<ItemCardType> items;

	/**
	 * Initializes list of Item Cards
	 */
	public ClientModel() {
		this.items = new ArrayList<ItemCardType>();
	}

	/**
	 * 
	 * @param coordinate
	 *            updates the current position of a player
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;

	}

	/**
	 * 
	 * @return the current position of a player
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * 
	 * @return list of item Cards of a player
	 */
	public List<ItemCardType> getItems() {
		return items;
	}

	/**
	 * 
	 * @param type
	 *            type of card to remove
	 */
	public void removeItem(ItemCardType type) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(type)) {
				items.remove(i);
				return;
			}
		}

	}
}
