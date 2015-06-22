package creator;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import model.*;

/**
 * @author Nicola
 * This class is used to create the 3 decks used in the game
 *
 */

public class CardsCreator {
	
	/**
	 * @return HatchCards, the decks of HatchCard, create 3 red hatchcard and 3 green card and shuffle the deck 
	 */
	
	public HatchCards createHatchCards() {
		
		List<HatchCard> hatchCard=new ArrayList<HatchCard>();
		for(int i=0;i<3;i++){
			hatchCard.add(new HatchCard(HatchCardColor.GREEN));
		}
		for(int i=0;i<3;i++){
			hatchCard.add(new HatchCard(HatchCardColor.RED));
		}
		Collections.shuffle(hatchCard);
		return new HatchCards(hatchCard);
	}
	
	/**
	 * @return SectorCards, the decks of SectorCard, create 10 noise in your sector (4 with item), 10 noise in any sector (4 with item) and 5
	 * silence, the shuffle the deck 
	 */
	
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
	
	/**
	 * @return ItemCards, the decks of ItemCard, create 3 sedative, 2 teletransport, 2 attack, 2 adrenaline,
	 *  1 defense, 2 spotlight and shuffle the deck 
	 */
	
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
