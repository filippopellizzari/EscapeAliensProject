package cli;

import java.util.ArrayList;
import java.util.List;

import model.Coordinate;
import model.ItemCardType;


public class ClientModel {

	private Coordinate coordinate;
	private final List<ItemCardType> items;
	
	public ClientModel(){
		this.items = new ArrayList<ItemCardType>();
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
				items.remove(i);
				return;
			}
		}
				
	}
}
