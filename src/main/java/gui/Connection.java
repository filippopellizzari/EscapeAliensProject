package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Connection extends JPanel {
	

	public Connection(){
		super(new BorderLayout());
		
		setSize(new Dimension(400, 300));
		setBackground(Color.BLACK);
		
		final JRadioButton rmiButton = new JRadioButton("RMI");
		rmiButton.setSelected(true);
		
		final JRadioButton socketButton = new JRadioButton("Socket");
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                if(rmiButton.isSelected()){
                System.out.println("You choosed RMI");
                }
                if(socketButton.isSelected()){
                    System.out.println("You choosed socket");
                }
                
            }
        });      
		
		ButtonGroup group = new ButtonGroup();
		group.add(rmiButton);
		group.add(socketButton);
		group.add(submit);
		
		
	
		//Put the radio buttons in a column in a panel.
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.setBackground(Color.GRAY);
        radioPanel.add(rmiButton);
        radioPanel.add(socketButton);
        radioPanel.add(submit);
  
 
        add(radioPanel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	}

}
