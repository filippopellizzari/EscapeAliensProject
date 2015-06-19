package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ChooseMap extends JPanel {

	private static final long serialVersionUID = 1L;

	public ChooseMap(final JFrame frame) {

		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		setBackground(Color.BLACK);

		// buttons
		final JRadioButton fermi = new JRadioButton("Fermi");
		fermi.setSelected(true);

		final JRadioButton galilei = new JRadioButton("Galilei");

		final JRadioButton galvani = new JRadioButton("Galvani");

		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String mapName = null;
				if (fermi.isSelected()) {
					mapName = fermi.getText();
				}
				if (galilei.isSelected()) {
					mapName = galilei.getText();
				}
				if (galvani.isSelected()) {
					mapName = galvani.getText();
				}
				System.out.println("You choosed "+mapName);
			
				setVisible(false);
				new GameTable(frame,mapName);

			}
		});

		// buttonGroup
		ButtonGroup group = new ButtonGroup();
		group.add(fermi);
		group.add(galilei);
		group.add(galvani);
		group.add(submit);

		// image
		ImageIcon image = loadImage("rsc" + File.separatorChar
				+ "chooseImage.jpg");
		JLabel imageContainer = new JLabel();
		imageContainer.setIcon(image);
		imageContainer.setSize(image.getIconWidth(), image.getIconHeight());

		// put buttons in a column in a panel.
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.setBorder(new TitledBorder("Scegli una mappa"));
		radioPanel.setBackground(Color.GRAY);
		radioPanel.add(fermi);
		radioPanel.add(galilei);
		radioPanel.add(galvani);
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
