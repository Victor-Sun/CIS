import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {

	private JButton start, guess;
	private JLabel inputLabel, outputLabel;
	private JTextField input;
	private JPanel north, south;
	private GameModel model;

	public GameFrame (GameModel model) {
		// create screen elements and add them to the frame



	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			if (event.getSource() == start) {
				// actions when the start button is clicked

			}
			else {
				// actions when the other button is clicked


			}
		}
	}
}