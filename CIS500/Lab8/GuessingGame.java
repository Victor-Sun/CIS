
import javax.swing.*;

public class GuessingGame {

	public static void main(String[] args) {
		GameModel model = new GameModel();
		JFrame frame = new GameFrame(model);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
	}

}