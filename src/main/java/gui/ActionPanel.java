package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ActionPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	public ActionPanel() {

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
	
		//discard combobox
		
		JComboBox<String> discardBox = new JComboBox<String>();
		discardBox.addItem("attack");
		discardBox.addItem("teleport");
		discardBox.addItem("sedatives");
		discardBox.addItem("spotlight");
		discardBox.addItem("adrenaline");
		
		discardBox.setEditable(false);
		
		JButton discard = new JButton("Discard");
		discard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(((JComboBox) e.getSource()).getSelectedItem());
				
			}
			
		});
		
		
		//other buttons
		JButton alienAttack = new JButton("ATTACK");
		JButton endTurn = new JButton("FINE TURNO");

		// testo esplicativo al passaggio mouse

		attack.setToolTipText("usa carta attacco");
		teleport.setToolTipText("usa carta teletrasporto");
		sedatives.setToolTipText("usa carta sedativi");
		spotlight.setToolTipText("usa carta spotlight");
		adrenaline.setToolTipText("usa carta adrenalina");
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
		discardPanel.add(discardBox);
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
