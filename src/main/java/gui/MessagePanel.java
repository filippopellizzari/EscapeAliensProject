package gui;
import javax.swing.*;

import connection.ClientData;
import controller.ActionType;
import dto.DTOSend;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MessagePanel extends JPanel implements ActionListener{


	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JTextField textField;
	
	private ClientData cd;
	
	public MessagePanel(ClientData cd){
		super(new GridBagLayout());
		this.cd = cd;
		setBackground(Color.GRAY);
		
		textArea = new JTextArea(20,28);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		textField = new JTextField(28);
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
		
		DTOSend dtoSend = null;
		try {
			dtoSend = new DTOSend(null, cd.getView().getNumberPlayer(), null,
					ActionType.CHAT, text);
		} catch (InterruptedException e1) {
			System.out.println("Problema invio chat: "+e1);
		}
		try {
			cd.clickOnDoMove(dtoSend);
		} catch (IOException e1) {
			System.out.println("Problema invio chat: "+e1);
		}

        textField.selectAll();
 
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
		
	}

	public JTextArea getTextArea() {
		return textArea;
	}


}
