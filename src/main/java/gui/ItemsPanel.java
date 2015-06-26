package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.ItemCardType;
import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

/**
 * This class is a panel which contains buttons of attack, teleport, sedatives
 * and adrenaline itemCard action. A player click on this buttons to do the
 * relative action. This panel is in the left Panel.
 * 
 * @author Filippo
 *
 */
public class ItemsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param cd
	 *            , clientData to do an action
	 */
	public ItemsPanel(final ClientData cd) {
		super(new GridLayout(0, 1));
		setBorder(new TitledBorder("Items"));
		setBackground(Color.GRAY);

		// items buttons
		JButton attack = new JButton("Attack", loadIcon("attack"));
		JButton teleport = new JButton("Teleport", loadIcon("teleport"));
		JButton sedatives = new JButton("Sedatives", loadIcon("sedatives"));
		JButton adrenaline = new JButton("Adrenaline", loadIcon("adrenaline"));

		// action listener
		attack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							ItemCardType.ATTACK, ActionType.USEITEM, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem attack item: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem attack item: " + e1);
				}

			}

		});

		teleport.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							ItemCardType.TELEPORT, ActionType.USEITEM, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem teleport item: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem teleport item: " + e1);
				}

			}

		});
		sedatives.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							ItemCardType.SEDATIVES, ActionType.USEITEM, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem sedatives item: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem sedatives item: " + e1);
				}
			}

		});

		adrenaline.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DTOSend dtoSend = null;
				try {
					dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(),
							ItemCardType.ADRENALINE, ActionType.USEITEM, null);
				} catch (InterruptedException e1) {
					System.out.println("Problem adrenaline item: " + e1);
				}
				try {
					cd.clickOnDoMove(dtoSend);
				} catch (IOException e1) {
					System.out.println("Problem adrenaline item: " + e1);
				}
			}

		});

		// testo esplicativo al passaggio mouse

		attack.setToolTipText("usa carta attacco");
		teleport.setToolTipText("usa carta teletrasporto");
		sedatives.setToolTipText("usa carta sedativi");
		adrenaline.setToolTipText("usa carta adrenalina");

		// add components
		add(attack);
		add(teleport);
		add(sedatives);
		add(adrenaline);
	}

	/**
	 * This method load the image symbols of the item Card buttons
	 * 
	 * @param name
	 *            , name of the itemCard
	 * @return image icon of an itemCard
	 */
	private ImageIcon loadIcon(String name) {
		try {
			return new ImageIcon(ImageIO.read(new File("rsc"
					+ File.separatorChar + name + ".png")));
		} catch (IOException e) {
			System.out.println("Error loadIcon: " + e);
			return null;
		}
	}
}
