package gui;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends JPanel implements ActionListener{

	private JTextArea textArea;
	private JTextField textField;
	
	public MessagePanel(){
		super(new GridBagLayout());
		
		textArea = new JTextArea(10,15);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		textField = new JTextField(15);
		textField.addActionListener(this);
		
		//Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
        textArea.append(text + "\n");
        textField.selectAll();
 
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
		
	}

}
