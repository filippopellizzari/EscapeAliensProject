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
		messagePanel = new MessagePanel();
		logPanel = new LogPanel();

		// layout
		add(coordPanel, BorderLayout.NORTH);
		add(messagePanel, BorderLayout.SOUTH);
		add(logPanel, BorderLayout.CENTER);

	}

	public LogPanel getLogPanel() {
		return logPanel;
	}

	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

}
