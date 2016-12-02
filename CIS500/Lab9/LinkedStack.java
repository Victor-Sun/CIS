import java.util.NoSuchElementException;

public class LinkedStack<T>{
    private int n;          // size of the stack
    private Node top;     	// top of stack
    
    private class Node {
        private T data;
        private Node next;
    }

    /**
     * Initializes an empty stack.
     */
    public LinkedStack() {
    	this.n = 0;
    	top = null;
    }

    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
    	return (n == 0);
    }

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements in the stack
     */
    public int size() {
    	return n;
    }

    /**
     * Adds elem to this stack.
     * @param elem the element to add
     */
    public void push(T elem) {
    	Node tmp = top;
    	top = new Node();
    	top.data = elem;
    	top.next = tmp;
    	n++;
//    	System.out.println(elem + " was pushed onto the stack!");
    }

    /**
     * Removes and returns the element most recently added to this stack.
     * @return the element most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T pop() {
    	if(top == null){
    		throw new NoSuchElementException();
    	}
    	T d = top.data;
    	top = top.next;
    	n--;
//    	System.out.println(d + " was popped from the stack!");
    	return d;
    }


    /**
     * Returns (but does not remove) the element most recently added to this stack.
     * @return the element most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public T peek() {
    	if(top == null){
    		throw new NoSuchElementException();
    	}
    	return top.data;
    }

    /**
     * Returns a string representation of this stack.
     * @return the sequence of elements in the stack in LIFO order, separated by spaces
     */
    public String toString() {
    	Node s = top;
    	StringBuilder sb = new StringBuilder();
    	while(s != null){
    		sb.append(s.data).append(" ");
    		s = s.next;
    	}
    	return "Stack: " + sb.toString();
    }
}

