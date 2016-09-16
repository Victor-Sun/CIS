public class Main {

	public static void main(String[] args) {
		
		// demonstration of writing tests without using JUnit
		Point p1 = new Point(4,5);
		Point p2 = new Point(3,4);
		Point p3 = new Point(4,5);
		
		System.out.println(p1);
		System.out.println(p1.getX());
		System.out.println(p1.getY());
		System.out.println(p1.equals(null));
		System.out.println(p1.equals(p1));
		System.out.println(p1.equals(p2));
		System.out.println(p2.equals(p3));
		System.out.println(p1.equals(p3));		
	}
}