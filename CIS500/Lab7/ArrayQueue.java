import java.util.ArrayList;

public class ArrayQueue<T> { 

	private ArrayList<T> list; 

	public ArrayQueue() { // instantiate the queue/list
		list = new ArrayList<T>();
	} 

	// returns true if the queue is empty, false otherwise 
	public boolean isEmpty() { 
		if(list.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
 
	// adds the element to the end of the queue 
	public void enQueue(T element) { 
			list.add(element);
			System.out.println("Added " + element + " to the queue.");
	}
 
	// removes the element at the front of the queue and returns it
	// Throws EmptyQueueException if queue is empty
	public T deQueue() throws IndexOutOfBoundsException { 
		if(list.isEmpty()){	
			// Need to figure out how to throw EmptyQueueException
			throw new IndexOutOfBoundsException("EmptyQueueException");
		} else {
			System.out.println("Removed " + list.get(0) + " from the queue.");
			list.remove(0);
		}
		return null;
	}
	
	// returns a String representation of the queue, which shows 
	// a list of elements in the queue
	public String toString() {
		return list.toString();
	}

	// Removes all occurrences of element from the queue 
	public void removeAll(T element) { 
		list.clear();
	}
}	

