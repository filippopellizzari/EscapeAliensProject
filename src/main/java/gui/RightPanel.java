package gui;

import javax.swing.*;

import connection.ClientData;


import java.awt.*;

public class RightPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private CoordinatePanel coordPanel;
	private LogPanel logPanel;
	private MessagePanel messagePanel;

	public RightPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		coordPanel = new CoordinatePanel(cd);
		logPanel = new LogPanel();
		messagePanel = new MessagePanel(cd);
		
		// layout
		add(coordPanel, BorderLayout.NORTH);
		add(logPanel, BorderLayout.CENTER);
		add(messagePanel, BorderLayout.SOUTH);
		
	}

	public LogPanel getLogPanel() {
		return logPanel;
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

}
