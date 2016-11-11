import java.util.Random;

public class GameModel {
    private Random rand;
    private int secretNum;
    private int count;
	
    public GameModel() {
    	rand = new Random();
    }
	
    public void start () {
    	secretNum = rand.nextInt(1000) + 1;
    	count = 0;
    }
    
    public int getNum(){
    	return secretNum;
    }
	
    public String verify (int guess) {
    	count++;
    	if (guess < secretNum)
    		return "Your guess is too low";
    	else if (guess > secretNum)
    		return "Your guess is too high";
    	else
    		return "You win in " + count + " attempts";
    }
}