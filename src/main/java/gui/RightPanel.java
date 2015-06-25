package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import connection.ClientData;

import java.awt.*;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CoordinatePanel coordPanel;
	
	private LogPanel myPositionPanel;
	private LogPanel logPanel;
	
	private MessagePanel messagePanel;

	public RightPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		coordPanel = new CoordinatePanel(cd);
		
		JPanel eastCenterPanel = new JPanel(new GridLayout(0,1));
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

	
	public LogPanel getMyPositionPanel() {
		return myPositionPanel;
	}

	public LogPanel getLogPanel() {
		return logPanel;
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

}
