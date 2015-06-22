package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameTable extends JPanel {

	private static final long serialVersionUID = 1L;

	public GameTable(JFrame frame, String mapName) {

		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(1400, 800));
		frame.setSize(new Dimension(1400, 800));
		frame.setLocationRelativeTo(null);
		setBackground(Color.BLACK);

		// map
		ImageIcon map = loadIcon(mapName);
		JLabel mapContainer = new JLabel();
		mapContainer.setIcon(map);
		mapContainer.setSize(map.getIconWidth(), map.getIconHeight());

		//left panel
		LeftPanel leftPanel = new LeftPanel();
		//right panel
		RightPanel rightPanel = new RightPanel();
		
		//layout
		add(leftPanel, BorderLayout.WEST);
		add(mapContainer, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
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
