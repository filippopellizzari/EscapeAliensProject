package gui;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import connection.ClientData;

public class GameTable extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private RightPanel rightPanel;

	public GameTable(JFrame frame, String mapName, ClientData cd) {

		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(1450, 800));
		frame.setSize(new Dimension(1450, 800));
		frame.setLocationRelativeTo(null);
		setBackground(Color.BLACK);

		// map
		ImageIcon map = loadIcon(mapName);
		JLabel mapContainer = new JLabel();
		mapContainer.setIcon(map);
		mapContainer.setSize(map.getIconWidth(), map.getIconHeight());

		// left panel
		LeftPanel leftPanel = new LeftPanel(cd);
		// rightPanel
		rightPanel = new RightPanel(cd);

		// layout
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

	public RightPanel getRightPanel() {
		return rightPanel;
	}
	
	

}
