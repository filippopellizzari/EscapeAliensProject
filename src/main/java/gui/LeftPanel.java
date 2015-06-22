package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class LeftPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	public LeftPanel() {

		super(new BorderLayout());
		setOpaque(true);

		// items buttons
		JButton attack = new JButton("attack", loadIcon("attack"));
		JButton teleport = new JButton("teleport", loadIcon("teleport"));
		JButton sedatives = new JButton("sedatives", loadIcon("sedatives"));
		JButton spotlight = new JButton("spotlight", loadIcon("spotlight"));
		JButton adrenaline = new JButton("adrenaline", loadIcon("adrenaline"));
		
		attack.addActionListener(new ItemListener());
		teleport.addActionListener(new ItemListener());
		sedatives.addActionListener(new ItemListener());
		spotlight.addActionListener(new ItemListener());
		adrenaline.addActionListener(new ItemListener());
	
		//discard table
		String[] columnNames = {"Item Type", "#"};
		final Object[][] data = {
				{" Attack", new Integer(0)},
				{" Teleport", new Integer(0)},
				{" Sedatives",new Integer(0)},
				{" Spotlight",new Integer(0)},
				{" Adrenaline",new Integer(0)}
		};
		
		final JTable itemsTable = new JTable(data, columnNames);
		itemsTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JButton discard = new JButton("Discard");
		discard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = itemsTable.getSelectedRow();
				System.out.println("discard "+data[row][0]);
				int num = (int) data[row][1];
				num--;
				data[row][1] = num;
				
			}
			
		});
		
		
		//other buttons
		JButton alienAttack = new JButton("ATTACK");
		alienAttack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ALIEN ATTACK");
				
			}
			
		});
		JButton endTurn = new JButton("FINE TURNO");
		endTurn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("FINE TURNO");
				
			}
			
		});

		// testo esplicativo al passaggio mouse

		attack.setToolTipText("usa carta attacco");
		teleport.setToolTipText("usa carta teletrasporto");
		sedatives.setToolTipText("usa carta sedativi");
		spotlight.setToolTipText("usa carta spotlight");
		adrenaline.setToolTipText("usa carta adrenalina");
		discard.setToolTipText("clicca per scartare carta oggetto selezionata");
		alienAttack.setToolTipText("attacco alieno");
		endTurn.setToolTipText("clicca quando vuoi finire il turno");

		// put item buttons in a column in the panel items
		JPanel items = new JPanel(new GridLayout(0, 1));
		items.setBorder(new TitledBorder("Items"));
		items.setBackground(Color.GRAY);
		items.add(attack);
		items.add(teleport);
		items.add(sedatives);
		items.add(spotlight);
		items.add(adrenaline);
		
		//discard panel
		JPanel discardPanel = new JPanel(new GridLayout(0,1));
		discardPanel.setBackground(Color.GRAY);
		discardPanel.add(itemsTable);
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
	
	private ImageIcon loadIcon(String name) {
		try {
			return new ImageIcon(ImageIO.read(new File("rsc"
					+ File.separatorChar + name + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
