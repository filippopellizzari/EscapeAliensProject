package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

public class LeftPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	public LeftPanel(final ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		//discard table
		String[] columnNames = {"Item Type", "#"};
		final Object[][] data = {
				{" Attack", new Integer(0)},
				{" Teleport", new Integer(0)},
				{" Sedatives",new Integer(0)},
				{" Spotlight",new Integer(0)},
				{" Adrenaline",new Integer(0)}
		};
		
		final JTable table = new JTable();
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		final DefaultTableModel dataModel = new DefaultTableModel()
		{

		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column)
		{
		return false;
		}

		}; 
		dataModel.setDataVector(data, columnNames);
		table.setModel(dataModel);
		
		
		JButton discard = new JButton("Discard");
		discard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				System.out.println("discard");
				System.out.println(dataModel.getValueAt(row, 0));
				int num = (int) dataModel.getValueAt(row, 1);
				num--;
				dataModel.setValueAt(num, row, 1);
				
			}
			
		});
		
		
		//other buttons
		JButton alienAttack = new JButton("ATTACK");
		alienAttack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
							ActionType.ATTACK, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem Attack action: "+e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem Attack action: "+e1);
				}
				
			}
			
		});
		JButton endTurn = new JButton("FINE TURNO");
		endTurn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
							ActionType.ENDTURN, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem EndTurn action: "+e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem EndTurn action: "+e1);
				}
				
			}
			
		});

		//testo esplicativo
		discard.setToolTipText("clicca per scartare carta oggetto selezionata");
		alienAttack.setToolTipText("clicca solo se sei alieno");
		endTurn.setToolTipText("clicca quando vuoi finire il turno");

		// put item buttons in a column in the panel items
		ItemsPanel items = new ItemsPanel(cd);
		
		//discard panel
		JPanel discardPanel = new JPanel(new GridLayout(0,1));
		discardPanel.setBackground(Color.GRAY);
		discardPanel.add(table);
		discardPanel.add(discard);
		
		
		//put action buttons in a column in another panel
		JPanel other = new JPanel(new GridLayout(0,1));
		other.setBackground(Color.GRAY);
		other.add(alienAttack);
		other.add(endTurn);
		
		//layout
		add(items, BorderLayout.NORTH);
		add(discardPanel, BorderLayout.CENTER);
		add(other, BorderLayout.SOUTH);
		
	}
	
}
