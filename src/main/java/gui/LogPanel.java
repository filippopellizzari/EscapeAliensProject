package gui;

import javax.swing.*;

import java.awt.*;

/**
 * This class is a panel which contains a text area to display particular
 * messages (message regarding coordinates)
 * 
 * @author Filippo
 *
 */
public class LogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	/**
	 * Creates Log Panel
	 */
	public LogPanel() {
		super(new GridBagLayout());
		setBackground(Color.GRAY);

		textArea = new JTextArea(6, 28);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Add Components to this panel.
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);

	}

	/**
	 * 
	 * @return reference to the text area of the log panel
	 */
	public JTextArea getTextArea() {
		return textArea;
	}

}
