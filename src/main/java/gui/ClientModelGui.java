package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Coordinate;
import model.ItemCardType;

public class ClientModelGui {

	private Coordinate coordinate;
	private final List<ItemCardType> items;
	private DefaultTableModel dataModel;
	
	public ClientModelGui(DefaultTableModel dataModel ){
		this.items = new ArrayList<ItemCardType>();
		this.dataModel = dataModel;
	}

	
	
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;

	}

	public Coordinate getCoordinate() {
		return coordinate;
	}


	public List<ItemCardType> getItems() {
		return items;
	}

	public void removeItem(ItemCardType type){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).equals(type)){
				updateDiscardPanel(type);
				items.remove(i);
				return;
			}
		}			
	}
	
	private void updateDiscardPanel(ItemCardType type){
		int row = 0;
		switch(type){
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
		int num = (int) dataModel.getValueAt(row, 1);
		num--;
		dataModel.setValueAt(num, row, 1);
	}
}
