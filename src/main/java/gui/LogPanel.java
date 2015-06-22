package gui;
import javax.swing.*;

import java.awt.*;

public class LogPanel extends JPanel{


	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	
	
	public LogPanel(){
		super(new GridBagLayout());
		setBackground(Color.GRAY);
		
		textArea = new JTextArea(10,20);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		
		//Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
		
		
	}
}
