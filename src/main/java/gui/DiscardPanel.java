package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.ClientData;

/**
 * This class is a panel which contains a JTable and a button. JTable is used to
 * display the number of player's item Card (for each type of card). "Discard"
 * button is used to discard an Item Card, after selecting it in the JTable.
 * 
 * This panel is in the North position, in the left side of game Table.
 * 
 * @author Filippo
 *
 */
public class DiscardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel dataModel;

	/**
	 * 
	 * @param cd
	 */
	public DiscardPanel(final ClientData cd) {
		super(new GridLayout(0, 1));

		setBackground(Color.GRAY);

		// discard table
		String[] columnNames = { "Item Type", "#" };
		final Object[][] data = { { " Attack", new Integer(0) },
				{ " Teleport", new Integer(0) },
				{ " Sedatives", new Integer(0) },
				{ " Spotlight", new Integer(0) },
				{ " Adrenaline", new Integer(0) },
				{ " Defense", new Integer(0) } };

		final JTable table = new JTable();
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		dataModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		dataModel.setDataVector(data, columnNames);
		table.setModel(dataModel);

		// discardButton
		JButton discardButton = new JButton("Discard");
		discardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				try {
					new DiscardAction().discard(cd, row);
				} catch (InterruptedException | IOException e1) {
					System.out.println("Problem discard: " + e1);
				}

			}

		});

		// testo esplicativo
		discardButton
				.setToolTipText("clicca per scartare carta oggetto selezionata");

		// add components
		add(discardButton);
		add(table);

	}

	/**
	 * 
	 * @return dataModel of the JTable
	 */
	public DefaultTableModel getDataModel() {
		return dataModel;
	}

}
