package gui;

import java.awt.*;

import javax.swing.*;

import connection.ClientData;

/**
 * This class is a panel, in the left side of GameTable. It contains itemsPanel,
 * discardPanel and southWestPanel.
 * 
 * @author Filippo
 *
 */
public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ItemsPanel itemsPanel;
	private DiscardPanel discardPanel;
	private SouthWestPanel southWestPanel;

	/**
	 * 
	 * @param cd
	 *            , clientData to do an action
	 */
	public LeftPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

		discardPanel = new DiscardPanel(cd);
		itemsPanel = new ItemsPanel(cd);
		southWestPanel = new SouthWestPanel(cd);

		// layout
		add(discardPanel, BorderLayout.NORTH);
		add(itemsPanel, BorderLayout.CENTER);
		add(southWestPanel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @return reference to discardPanel
	 */
	public DiscardPanel getDiscardPanel() {
		return discardPanel;
	}

	/**
	 * 
	 * @return refernce to southWestPanel
	 */
	public SouthWestPanel getSouthWestPanel() {
		return southWestPanel;
	}

}
