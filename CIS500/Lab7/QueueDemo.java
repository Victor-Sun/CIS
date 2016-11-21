
public class QueueDemo {

	public static void main(String[] args) {
		// create a queue of strings
		ArrayQueue<String> aQ = new ArrayQueue<String>();
		// add three strings to the queue
		aQ.enQueue("ABC");
		aQ.enQueue("DEF");
		aQ.enQueue("GHI");
		// remove two strings from the queue
		aQ.deQueue();
		aQ.deQueue();
		// display all elements in the queue
		System.out.println(aQ.toString());
		// add two strings from the queue
		aQ.enQueue("JKL");
		aQ.enQueue("MNO");
		// remove one string from the queue
		aQ.deQueue();
		// display the list of strings in the queue
		System.out.println(aQ.toString());
		System.out.println("");
		
		
		// create a queue of coins
		ArrayQueue<Coin> coins = new ArrayQueue<Coin>();
		// add three coins to the queue
		Coin p = new Coin(0.01, "Penny");
		Coin n = new Coin(0.05, "Nickel");
		Coin d = new Coin(0.10, "Dime");
		coins.enQueue(p);
		coins.enQueue(n);
		coins.enQueue(d);
		// remove one coin from the queue
		coins.deQueue();
		//display the list of coins in the queue
		System.out.println(coins.toString());
	}
}