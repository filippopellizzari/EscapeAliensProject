package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Coordinate;
import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class CoordinatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public CoordinatePanel(final ClientData cd) {
		super(new GridLayout(0, 2));

		setBorder(new TitledBorder("Coordinates"));
		setBackground(Color.GRAY);

		
		//coord comboBox
		final JComboBox<Character> letteraBox = new JComboBox<Character>();
		for (int i = 65; i <= 87; i++) {
			letteraBox.addItem((char) i);
		}
		final JComboBox<Integer> numeroBox = new JComboBox<Integer>();
		for (int i = 1; i <= 14; i++) {
			numeroBox.addItem(new Integer(i));
		}

		//move button
		JButton moveButton = new JButton("MOVE");
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				char lettera = (char) letteraBox.getSelectedItem();
				int xMove = (int) lettera - 64;
				int yMove = (int) numeroBox.getSelectedItem();

				Coordinate coordMove = new Coordinate(xMove, yMove);
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(coordMove, cd.getView()
							.getNumberPlayer(), null, ActionType.MOVE, null);
				} catch (InterruptedException e2) {
					System.out.println("Problem Move Action: " + e2);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem Move Action: " + e1);
				}

			}

		});
		//noise button
		JButton noiseButton = new JButton("NOISE");
		noiseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char lettera = (char) letteraBox.getSelectedItem();
				int xNoise = (int) lettera - 64;
				int yNoise = (int) numeroBox.getSelectedItem();

				Coordinate coordMove = new Coordinate(xNoise, yNoise);
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(coordMove, cd.getView()
							.getNumberPlayer(), null,
							ActionType.SELECTSECTORNOISE, null);
				} catch (InterruptedException e2) {
					System.out.println("Problem Noise Action: " + e2);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem Noise Action: " + e1);
				}

			}

		});
		//spotlight button
		JButton spotlightButton = new JButton("SPOTLIGHT");
		spotlightButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				char lettera = (char) letteraBox.getSelectedItem();
				int xSpot = (int) lettera - 64;
				int ySpot = (int) numeroBox.getSelectedItem();

				Coordinate coordSpot = new Coordinate(xSpot, ySpot);
				
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(coordSpot, cd.getView().getNumberPlayer(),
							ItemCardType.SPOTLIGHT, ActionType.USEITEM, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem spotlight item: "+e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem spotlight item: "+e1);
				}
				
			}
			
		});
		
		// testo esplicativo al passaggio mouse
		String text = "Seleziona coordinata, prima di cliccare";
		moveButton.setToolTipText(text);
		noiseButton.setToolTipText(text);
		spotlightButton.setToolTipText(text);
		
		//add components
		add(letteraBox);
		add(numeroBox);
		add(moveButton);
		add(noiseButton);
		add(spotlightButton);
	}
}
