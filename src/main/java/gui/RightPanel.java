package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel{

	
	private static final long serialVersionUID = 1L;


	
	public RightPanel(){
		
		super(new BorderLayout());
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		//coordinates area
		final JComboBox letteraBox = new JComboBox();
		for(int i=65; i<=87; i++){
			letteraBox.addItem((char)i);
		}
		final JComboBox numeroBox = new JComboBox();
		for(int i=1; i<=14; i++){
			numeroBox.addItem(new Integer(i));
		}
		
		JButton moveButton = new JButton("MOVE");
		moveButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MOVE");
			}
			
			
		});
		JButton noiseButton = new JButton("NOISE");
		noiseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(letteraBox.getSelectedItem());
				System.out.println(numeroBox.getSelectedItem());
				System.out.println("NOISE ");
				
			}
			
		});
		
		
		//coordinates Panel
		JPanel coordPanel = new JPanel(new GridLayout(0,2));
		coordPanel.setBorder(new TitledBorder("Coordinates"));
		coordPanel.setBackground(Color.GRAY);
		coordPanel.add(letteraBox);
		coordPanel.add(numeroBox);
		coordPanel.add(moveButton);
		coordPanel.add(noiseButton);
		
		//messagePanel
		MessagePanel messagePanel = new MessagePanel();
		
		//logPanel
		LogPanel logPanel = new LogPanel();
	
		
		//layout
		add(coordPanel, BorderLayout.NORTH);
		add(messagePanel, BorderLayout.SOUTH);
		add(logPanel, BorderLayout.CENTER);
		
	}

	
	
	
	
}
