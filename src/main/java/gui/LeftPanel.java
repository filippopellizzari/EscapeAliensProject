package gui;

import java.awt.*;

import javax.swing.*;

import connection.ClientData;

public class LeftPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;
	
	private ItemsPanel itemsPanel;
	private DiscardPanel discardPanel;
	private SouthWestPanel southWestPanel;

	public LeftPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		discardPanel = new DiscardPanel(cd);
		itemsPanel = new ItemsPanel(cd);
		southWestPanel = new SouthWestPanel(cd);

		//layout
		add(discardPanel, BorderLayout.NORTH);
		add(itemsPanel, BorderLayout.CENTER);
		add(southWestPanel, BorderLayout.SOUTH);
		
	}

	public DiscardPanel getDiscardPanel() {
		return discardPanel;
	}

	public SouthWestPanel getSouthWestPanel() {
		return southWestPanel;
	}
	
	
	
}
