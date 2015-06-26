package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import connection.ClientData;

import java.awt.*;

/**
 * This class is the panel in the right side of Game Table. It contains
 * coordinatePanel, 2 logPanels and the messagePanel. The first logPanel is used
 * to display the current position of a player during the game. The second
 * logPanel is used to display events regarding positions of players (e.g
 * spotlight, noise, personal moves).
 * 
 * 
 * @author Filippo
 *
 */
public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CoordinatePanel coordPanel;

	private LogPanel myPositionPanel;
	private LogPanel logPanel;

	private MessagePanel messagePanel;

	/**
	 * 
	 * @param cd
	 *            , reference to ClientData
	 */
	public RightPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		coordPanel = new CoordinatePanel(cd);

		JPanel eastCenterPanel = new JPanel(new GridLayout(0, 1));
		myPositionPanel = new LogPanel();
		myPositionPanel.setBorder(new TitledBorder("My Position"));

		logPanel = new LogPanel();
		eastCenterPanel.add(myPositionPanel);
		eastCenterPanel.add(logPanel);

		messagePanel = new MessagePanel(cd);

		// layout
		add(coordPanel, BorderLayout.NORTH);
		add(eastCenterPanel, BorderLayout.CENTER);
		add(messagePanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @return reference to myPositionPanel
	 */
	public LogPanel getMyPositionPanel() {
		return myPositionPanel;
	}

	/**
	 * 
	 * @return reference to logPanel
	 */
	public LogPanel getLogPanel() {
		return logPanel;
	}

	/**
	 * 
	 * @return reference to messagePanel
	 */
	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

}
