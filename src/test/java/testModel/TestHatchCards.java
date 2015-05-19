package testModel;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.*;
import creator.*;

import org.junit.Test;

public class TestHatchCards {
	
	
	@Test
	public void testDrawDiscard() {
		CardsCreator cardCreator=new CardsCreator();
		HatchCards listHatch=cardCreator.createHatchCards();
		int green=0;
		int red=0;
		HatchCard hatchCard=listHatch.draw();
		assertTrue(hatchCard instanceof HatchCard);
		for(int i=0;i<5;i++) {
			if(listHatch.draw().getColor()==HatchCardColor.GREEN) green++;
			else red++;
		}
			
		
	}
	
		
	

}
