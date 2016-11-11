import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GameFrame extends JFrame {

	private JButton start, guess;
	private JLabel inputLabel, outputLabel;
	private JTextField input;
	private JPanel north, south, center;
	private GameModel model;
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEGHT = 100;
	
	public GameFrame (GameModel model) {
		// create screen elements and add them to the frame
		setSize(FRAME_WIDTH,FRAME_HEGHT);
		this.setLayout(new BorderLayout());
		
		//Initializing buttons
		start = new JButton("Start Game");
		guess = new JButton("Guess");
		
		//Initializing labels
		inputLabel = new JLabel("Enter Your Guess: ");
		outputLabel = new JLabel("Click the Start button to begin a new game.");
		
		//Initializing Textfield
		input = new JTextField();
		input.setColumns(20);
		
		//Initializing Panels
		north = new JPanel();
		south = new JPanel();
		center = new JPanel();

		//Adding panels and other things
		north.add(inputLabel);
		north.add(input);
		
		center.add(outputLabel);

		south.add(start);
		south.add(guess);
		
		center.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
		south.setBorder(new TitledBorder(new EtchedBorder(), "Options"));
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		
		ButtonListener handler = new ButtonListener();
		start.addActionListener(handler);
		guess.addActionListener(handler);
	}
	
	private class ButtonListener implements ActionListener {
		String a;
		int x;
		public void actionPerformed (ActionEvent event) {
			if (event.getSource() == start) {
				model = new GameModel();
				model.start();
				outputLabel.setText("Guess a number between 0 and 1000!");
			}
			else {
				try{
					x = Integer.parseInt(input.getText());
					a = model.verify(x);
					outputLabel.setText(a);
					System.out.println(model.getNum());
				} catch(NumberFormatException e){
					outputLabel.setText("Guess can only be a number!");
				}
			}
		}
	}
}