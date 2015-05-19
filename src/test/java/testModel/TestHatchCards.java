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
		HatchCard hatchCard;
		int green=0;
		int red=0;
		for(int i=0;i<6;i++)
		{
			hatchCard=listHatch.draw();
			if(hatchCard instanceof HatchCard)
			{
				if(listHatch.draw().getColor()==HatchCardColor.GREEN) green++;
				else red++;
			}
		}
		assertTrue(red==3&& green==3);
		assertEquals(listHatch.draw(),null);
	}
}
