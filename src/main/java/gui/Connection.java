package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Connection extends JPanel {

	private static final long serialVersionUID = 1L;

	public Connection(final JFrame frame) {

		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		setBackground(Color.BLACK);

		// buttons
		final JRadioButton rmiButton = new JRadioButton("RMI");
		rmiButton.setSelected(true);

		final JRadioButton socketButton = new JRadioButton("Socket");

		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (rmiButton.isSelected()) {
					System.out.println("You choosed RMI");
				}
				if (socketButton.isSelected()) {
					System.out.println("You choosed socket");
				}
				setVisible(false);
				new ChooseMap(frame);

			}
		});

		// buttonGroup
		ButtonGroup group = new ButtonGroup();
		group.add(rmiButton);
		group.add(socketButton);
		group.add(submit);

		// image
		ImageIcon image = loadImage("rsc" + File.separatorChar
				+ "startImage.jpg");
		JLabel imageContainer = new JLabel();
		imageContainer.setIcon(image);
		imageContainer.setSize(image.getIconWidth(), image.getIconHeight());

		// put buttons in a column in a panel
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.setBorder(new TitledBorder("Scegli la connessione"));
		radioPanel.setBackground(Color.GRAY);
		radioPanel.add(rmiButton);
		radioPanel.add(socketButton);
		radioPanel.add(submit);

		// layout
		add(radioPanel, BorderLayout.SOUTH);
		add(imageContainer, BorderLayout.WEST);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	private ImageIcon loadImage(String path) {
		try {
			return new ImageIcon(ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
