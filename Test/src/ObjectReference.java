
public class ObjectReference {

	public static void mystery(Counter a, Counter b) {
			a.increment();
			b = new Counter(0);
			b.increment(); 
		 }

		 public static void main(String[] args) {
			Counter c1 = new Counter(5);
			Counter c2 = new Counter(10);
			mystery(c1, c2);
			System.out.println("Line 1: " + c1 + " " + c2);
			mystery(c1, c1);
			System.out.println("Line 2: " + c1 + " " + c2);
			mystery(c2, c1);
			System.out.println("Line 3: " + c1 + " " + c2);
		}


}
