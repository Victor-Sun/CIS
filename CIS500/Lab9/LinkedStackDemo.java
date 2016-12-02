public class LinkedStackDemo {
	
	public static void main(String[] args) {
		
		// create a stack of strings
		LinkedStack<String> ls = new LinkedStack<String>();
		
		// push two strings onto the stack
		ls.push("a");
		ls.push("b");
		// pop a string from the stack
		ls.pop();
		
		// display all elements in the stack
		System.out.println(ls.toString());
		
		// push one string from the stack
		ls.push("c");
		
		// pop one string from the stack
		ls.pop();
		
		// display the list of strings in the stack
		System.out.println(ls.toString());

	}
}