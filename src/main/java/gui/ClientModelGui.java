package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Coordinate;
import model.ItemCardType;

/**
 * This class represents the model of data which is useful to display to client
 * 
 * @author Filippo
 *
 */
public class ClientModelGui {

	private Coordinate coordinate;
	private final List<ItemCardType> items;
	private DefaultTableModel dataModel;

	/**
	 * Initialize list of item Cards of a player
	 * 
	 * @param dataModel
	 *            , data of the JTable
	 */
	public ClientModelGui(DefaultTableModel dataModel) {
		this.items = new ArrayList<ItemCardType>();
		this.dataModel = dataModel;
	}

	/**
	 * 
	 * @param coordinate
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;

	}

	/**
	 * 
	 * @return current position of a player
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * 
	 * @return list of item Cards
	 */
	public List<ItemCardType> getItems() {
		return items;
	}

	/**
	 * 
	 * @param type
	 *            type of item card to add in the list and in the JTable
	 */
	public void addItem(ItemCardType type) {
		items.add(type);
		int row = chooseRow(type);
		int num = (int) dataModel.getValueAt(row, 1);
		num++;
		System.out.println(type+","+ num);
		dataModel.setValueAt(num, row, 1);
	}

	/**
	 * 
	 * @param type
	 *            type of item Card to remove from the list and from JTable
	 */
	public void removeItem(ItemCardType type) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).equals(type)) {
				items.remove(i);
				int row = chooseRow(type);
				int num = (int) dataModel.getValueAt(row, 1);
				num--;
				dataModel.setValueAt(num, row, 1);
				return;
			}
		}
	}

	/**
	 * 
	 * @param type
	 *            of item Card
	 * @return the row in JTable associated to the type of item Card
	 */
	private int chooseRow(ItemCardType type) {
		int row = 0;
		switch (type) {
		case ATTACK:
			row = 0;
			break;
		case TELEPORT:
			row = 1;
			break;
		case SEDATIVES:
			row = 2;
			break;
		case SPOTLIGHT:
			row = 3;
			break;
		case ADRENALINE:
			row = 4;
			break;
		case DEFENSE:
			row = 5;
			break;
		default:
			break;
		}
		return row;
	}
}
