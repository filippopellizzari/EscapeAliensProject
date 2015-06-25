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


public class DiscardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel dataModel;
	
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

		//discardButton
		JButton discardButton = new JButton("Discard");
		discardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				System.out.println("discard");
				System.out.println(dataModel.getValueAt(row, 0));

				try {
					new DiscardAction().discard(cd, row);
				} catch (InterruptedException | IOException e1) {
					System.out.println("Problem discard: "+e1);
				}
				/*
				int num = (int) dataModel.getValueAt(row, 1);
				num--;
				dataModel.setValueAt(num, row, 1);
*/
			}

		});

		// testo esplicativo
		discardButton.setToolTipText("clicca per scartare carta oggetto selezionata");

		// add components
		add(table);
		add(discardButton);
	}

	public DefaultTableModel getDataModel() {
		return dataModel;
	}
	
	
}
