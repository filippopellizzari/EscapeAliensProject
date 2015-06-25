package gui;

import java.awt.*;

import javax.swing.*;

import connection.ClientData;

public class Gui {
	
	private GameTable gameTable;
	
	public void createAndShowGUI(String mapName, ClientData cd) {

		JFrame frame = new JFrame("EscapeFromTheAliens");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.setPreferredSize(new Dimension(600, 800));
		frame.setSize(600, 800);

		// frame al centro dello schermo
		frame.setLocationRelativeTo(null);
		
		
		setGameTable(new GameTable(frame, mapName, cd));
		
		frame.pack();
		frame.setVisible(true);

	}

	public GameTable getGameTable() {
		return gameTable;
	}

	public void setGameTable(GameTable gameTable) {
		this.gameTable = gameTable;
	}

}
