package creator;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import model.*;

public class CardsCreator {
	
	public HatchCards createHatchCards() {
		
		List<HatchCard> hatchCard=new ArrayList<HatchCard>();
		for(int i=0;i<3;i++){
			hatchCard.add(new HatchCard(true));
		}
		for(int i=0;i<3;i++){
			hatchCard.add(new HatchCard(false));
		}
		
		Collections.shuffle(hatchCard);
		return new HatchCards(hatchCard);
	}
	
	public SectorCards createSectorCards() {
		
		List<SectorCard> sectorCard=new ArrayList<SectorCard>();
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,SectorCardType.NOISEANY));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,SectorCardType.NOISEANY));
		}
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,SectorCardType.NOISEYOUR));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,SectorCardType.NOISEYOUR));
		}
		for(int i=0;i<5;i++){
			sectorCard.add(new SectorCard(false,SectorCardType.SILENCE));
		}
		
		Collections.shuffle(sectorCard);
		return new SectorCards(sectorCard);
	}
	
	public ItemCards createItemCards() {
		
		List<ItemCard> itemCards=new ArrayList<ItemCard>();
		for(int i=0;i<2;i++){
			itemCards.add(new ItemCard(ItemCardType.ADRENALINE));
		}
		for(int i=0;i<2;i++){
			itemCards.add(new ItemCard(ItemCardType.ATTACK));
		}
		for(int i=0;i<3;i++){
			itemCards.add(new ItemCard(ItemCardType.SEDATIVES));
		}
		for(int i=0;i<2;i++){
			itemCards.add(new ItemCard(ItemCardType.SPOTLIGHT));
		}
		for(int i=0;i<2;i++){
			itemCards.add(new ItemCard(ItemCardType.TELEPORT));
		}
		itemCards.add(new ItemCard(ItemCardType.DEFENSE));
		
		Collections.shuffle(itemCards);
		return new ItemCards(itemCards);
	}
}
