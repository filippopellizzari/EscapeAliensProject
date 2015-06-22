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
		
		//move area
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
				System.out.println("MOVE ");
				
			}
			
		});
		
		
		
		//movePanel
		JPanel movePanel = new JPanel(new GridLayout(0,2));
		movePanel.setBorder(new TitledBorder("Move"));
		movePanel.setBackground(Color.GRAY);
		movePanel.add(letteraBox);
		movePanel.add(numeroBox);
		movePanel.add(moveButton);
		
		//messagePanel
		MessagePanel messagePanel = new MessagePanel();
		
		//layout
		add(movePanel, BorderLayout.NORTH);
		add(messagePanel, BorderLayout.SOUTH);
		
	}
	
	
}
