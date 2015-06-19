package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GameTable extends JPanel {

	private static final long serialVersionUID = 1L;

	public GameTable(JFrame frame, String mapName) {

		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(1400, 800));
		frame.setSize(new Dimension(1400, 800));
		setBackground(Color.BLACK);

		// map
		ImageIcon map = loadIcon(mapName);
		JLabel mapContainer = new JLabel();
		mapContainer.setIcon(map);
		mapContainer.setSize(map.getIconWidth(), map.getIconHeight());

		// buttons
		JButton attack = new JButton("attack", loadIcon("attack"));
		JButton teleport = new JButton("teleport", loadIcon("teleport"));
		JButton sedatives = new JButton("sedatives", loadIcon("sedatives"));
		JButton spotlight = new JButton("spotlight", loadIcon("spotlight"));
		JButton adrenaline = new JButton("adrenaline", loadIcon("adrenaline"));

		// testo esplicativo al passaggio mouse

		attack.setToolTipText("attack");
		teleport.setToolTipText("teleport");
		sedatives.setToolTipText("sedatives");
		spotlight.setToolTipText("spotlight");
		adrenaline.setToolTipText("adrenaline");

		// put buttons in a column in a panel
		JPanel items = new JPanel(new GridLayout(0, 1));
		items.setBorder(new TitledBorder("Items"));
		items.setBackground(Color.GRAY);
		items.add(attack);
		items.add(teleport);
		items.add(sedatives);
		items.add(spotlight);
		items.add(adrenaline);

		//layout
		add(items, BorderLayout.WEST);
		add(mapContainer, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
