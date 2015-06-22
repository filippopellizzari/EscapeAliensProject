package cli;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import model.Coordinate;
import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;
/**
 * 
 * 
 * @author Filippo
 *
 */
public class ClientPlay {
	
	
	private ClientData cd;
	private DTOSend dtoSend;
	private Scanner in;
	/**
	 * 
	 * @param cd data 
	 */
	public ClientPlay(ClientData cd){
		this.cd = cd;
	}

	
	public void play() throws UnknownHostException, ClassNotFoundException, IOException, InterruptedException{

		in = new Scanner(System.in);
		Thread.sleep(5000);
			System.out.println("Fai un'azione:\n 1: MOVE\n 2: ATTACK\n 3: USE ITEM CARD\n "
					+ "4: DISCARD ITEM CARD\n 5: DRAW SECTOR CARD\n 6: SELECT FOR NOISE IN ANY SECTOR\n "
					+ "7: END TURN\n 8: CHAT");	
			
			int azione = in.nextInt();
			switch(azione){
			case 1:
				move();
				break;
			case 2:
				attack();
				break;
			case 3: 
				useItem();
				break;
			case 4:
				discard();
				break;
			case 5:
				draw();
				break;
			case 6:
				noise();
				break;
			case 7: 
				endTurn();
				break;
			case 8:
				chat();
				break;
			default:
				break;
			}
			//in.close();
	}	
	
	
	private void move() throws UnknownHostException, IOException, InterruptedException{
		System.out.println("Inserisci le coordinate:\n Lettera:\n");
		char letteraMove = in.next().charAt(0);
		int xMove = (int)letteraMove - 96; //converto char in intero
		System.out.println("Numero:\n");
		int yMove = in.nextInt();
		Coordinate coordMove = new Coordinate (xMove, yMove);
		dtoSend = new DTOSend(coordMove, cd.getView().getNumberPlayer(), null, ActionType.MOVE, null);
		cd.clickOnDoMove(dtoSend);
	}
	
	private void attack() throws UnknownHostException, IOException, InterruptedException{
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null, ActionType.ATTACK, null);
		cd.clickOnDoMove(dtoSend);
	}
	
	private void useItem() throws UnknownHostException, IOException, InterruptedException{
		System.out.println("Scegli tipo:\n 1: ATTACK\n 2: TELEPORT\n 3: SEDATIVES\n 4: SPOTLIGHT\n 5: ADRENALINE");
		int typeToUse = in.nextInt();
		switch(typeToUse){
		case 1: 
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.ATTACK, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 2:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.TELEPORT, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 3:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.SEDATIVES, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 4:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.SPOTLIGHT, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 5:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.ADRENALINE, ActionType.USEITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		default:
			break;
		}
	}
	
	private void discard() throws UnknownHostException, IOException, InterruptedException{
		System.out.println("Scegli tipo:\n 1: ATTACK\n 2: TELEPORT\n 3: SEDATIVES\n 4: SPOTLIGHT\n 5: ADRENALINE");
		int typeToDiscard = in.nextInt();
		switch(typeToDiscard){
		case 1: 
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.ATTACK, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 2:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.TELEPORT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 3:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.SEDATIVES, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 4:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.SPOTLIGHT, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		case 5:
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), ItemCardType.ADRENALINE, ActionType.DISCARDITEM, null);
			cd.clickOnDoMove(dtoSend);
			break;
		default:
			break;
		}
	}
	
	private void draw() throws UnknownHostException, IOException, InterruptedException{
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null, ActionType.DRAWSECTORCARD, null);
		cd.clickOnDoMove(dtoSend);
	}
	
	private void noise() throws UnknownHostException, IOException, InterruptedException{
		System.out.println("Inserisci le coordinate:\n Lettera:\n");
		char letteraSelect = in.next().charAt(0);
		int xSelect = (int)letteraSelect - 96; //converto char in intero
		System.out.println("Numero:\n");
		int ySelect = in.nextInt();
		Coordinate coordSelect = new Coordinate (xSelect,ySelect);
		dtoSend = new DTOSend(coordSelect, cd.getView().getNumberPlayer(), null, ActionType.MOVE, null);
		cd.clickOnDoMove(dtoSend);
	}
	
	private void endTurn() throws UnknownHostException, IOException, InterruptedException{
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null, ActionType.ENDTURN, null);
		cd.clickOnDoMove(dtoSend);
	}
	
	private void chat() throws UnknownHostException, IOException, InterruptedException{
		String mess = in.nextLine();
		dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null, ActionType.CHAT, mess);
		cd.clickOnDoMove(dtoSend);
	}
	
	
}
