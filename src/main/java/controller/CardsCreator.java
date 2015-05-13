package controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import model.*;

public class CardsCreator {
	
	public HatchCards HatchCardsCreator() {
		
		List<HatchCard> hatchCard=new ArrayList<HatchCard>();
		for(int i=0;i<2;i++){
			hatchCard.add(new HatchCard(true));
		}
		for(int i=0;i<4;i++){
			hatchCard.add(new HatchCard(false));
		}
		Collections.shuffle(hatchCard);
		return new HatchCards(hatchCard);
	}
	
	public SectorCards SectorCardsCreator() {
		
		List<SectorCard> sectorCard=new ArrayList<SectorCard>();
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,TypeSectorCard.NoiseAny));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.NoiseAny));
		}
		for(int i=0;i<4;i++){
			sectorCard.add(new SectorCard(true,TypeSectorCard.NoiseYour));
		}
		for(int i=0;i<6;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.NoiseYour));
		}
		for(int i=0;i<5;i++){
			sectorCard.add(new SectorCard(false,TypeSectorCard.Silence));
		}
		Collections.shuffle(sectorCard);
		return new SectorCards(sectorCard);
	}
	
	public ItemCards ItemCardsCreator() {
		
		List<ItemCard> itemCard=new ArrayList<ItemCard>();
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.Adrenaline));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.Attack));
		}
		itemCard.add(new ItemCard(TypeItemCard.Defense));
		for(int i=0;i<3;i++){
			itemCard.add(new ItemCard(TypeItemCard.Sedatives));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.SpotLight));
		}
		for(int i=0;i<2;i++){
			itemCard.add(new ItemCard(TypeItemCard.Teleport));
		}
		Collections.shuffle(itemCard);
		return new ItemCards(itemCard);
	}
}
