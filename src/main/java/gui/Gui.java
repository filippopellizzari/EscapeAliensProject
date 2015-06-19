package gui;

import java.awt.*;

import javax.swing.*;

public class Gui {
	
	private static void createAndShowGUI() {

		JFrame frame = new JFrame("EscapeFromTheAliens");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.setPreferredSize(new Dimension(600,800));
		frame.setSize(600, 800);
		
		//frame al centro dello schermo
		frame.setLocationRelativeTo(null); 
		
		new Connection(frame);
		
		frame.pack();
		frame.setVisible(true);	
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				createAndShowGUI();

			}

		});
	}

}
