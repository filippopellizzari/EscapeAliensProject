package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Gui {
	
	
	private static void createAndShowGUI() {

		JFrame frame = new JFrame("EscapeFromTheAliens");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setLocationRelativeTo(null); //frame al centro
		
		JComponent connection = new Connection();
		connection.setOpaque(true);
        frame.setContentPane(connection);
		
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
