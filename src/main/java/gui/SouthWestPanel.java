package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is a Panel which contains a JTextArea for general players' info
 * (number, playerType) and 3 buttons: "pesca", "attack" e "fine Turno". The
 * first is used to to a draw action, the second is used to do an alien attack,
 * the third is used to end the turn.
 * 
 * This panel is in the south position of the leftPanel.
 * 
 * @author Filippo
 *
 */
public class SouthWestPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea textArea;

	/**
	 * 
	 * @param cd
	 *            ClientData, used to do actions
	 */
	public SouthWestPanel(final ClientData cd) {
		super(new GridLayout(0, 1));
		setBackground(Color.GRAY);

		// info textArea

		textArea = new JTextArea(3, 15);
		textArea.setEditable(false);

		// buttons
		JButton drawButton = new JButton("PESCA");
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							null, ActionType.DRAWSECTORCARD, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem Draw Action: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem Draw Action: " + e1);
				}

			}

		});

		JButton alienAttack = new JButton("ATTACK");
		alienAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							null, ActionType.ATTACK, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem Attack action: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem Attack action: " + e1);
				}

			}

		});
		JButton endTurn = new JButton("FINE TURNO");
		endTurn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							null, ActionType.ENDTURN, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem EndTurn action: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem EndTurn action: " + e1);
				}

			}

		});

		// testo esplicativo
		drawButton
				.setToolTipText("clicca per pescare carta settore pericoloso");
		alienAttack.setToolTipText("clicca solo se sei alieno");
		endTurn.setToolTipText("clicca quando vuoi finire il turno");

		// add components
		add(textArea);
		add(drawButton);
		add(alienAttack);
		add(endTurn);

	}

	/**
	 * 
	 * @return reference to the textArea
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

}
