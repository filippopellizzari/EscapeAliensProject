package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String item = e.getActionCommand();
		System.out.println(item);
		
	}

}
