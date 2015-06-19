package gui;

import java.awt.*;

import javax.swing.*;

public class GameTable extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public GameTable(JFrame frame){
		
		super(new BorderLayout());
		setOpaque(true);
		frame.setContentPane(this);
		frame.setPreferredSize(new Dimension(1000,800));
		frame.setSize(new Dimension(1000,800));
		setBackground(Color.BLACK);
		
		JLabel label = new JLabel("GameTable");
		add(label, BorderLayout.WEST);
	}

}
