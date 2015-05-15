package controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import model.*;

public class CardsCreator {
	
	public HatchCards hatchCardsCreator() {
		
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
	
	public SectorCards sectorCardsCreator() {
		
		List<SectorCard> sectorCard=new ArrayList<SectorCard>();
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,TypeSectorCard.NOISEANY));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.NOISEANY));
		}
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,TypeSectorCard.NOISEYOUR));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.NOISEYOUR));
		}
		for(int i=0;i<5;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.SILENCE));
		}
		Collections.shuffle(sectorCard);
		return new SectorCards(sectorCard);
	}
	
	public ItemCards itemCardsCreator() {
		
		List<ItemCard> itemCard=new ArrayList<ItemCard>();
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.ADRENALINE));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.ATTACK));
		}
		for(int i=0;i<3;i++){
			itemCard.add(new ItemCard(TypeItemCard.SEDATIVES));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.SPOTLIGHT));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.TELEPORT));
		}
		itemCard.add(new ItemCard(TypeItemCard.DEFENSE));
		Collections.shuffle(itemCard);
		return new ItemCards(itemCard);
	}
}
