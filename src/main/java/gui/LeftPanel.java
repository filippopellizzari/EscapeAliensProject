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
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		itemsPanel = new ItemsPanel(cd);
		discardPanel = new DiscardPanel(cd);
		southWestPanel = new SouthWestPanel(cd);

		//layout
		add(itemsPanel, BorderLayout.NORTH);
		add(discardPanel, BorderLayout.CENTER);
		add(southWestPanel, BorderLayout.SOUTH);
		
	}

	public DiscardPanel getDiscardPanel() {
		return discardPanel;
	}
	
	
}
