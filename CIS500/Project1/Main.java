
public class Main {

	public static void main(String[] args) {
		Point p1 = new Point(1,2);
		Point p2 = new Point(3,4);
		Line l1 = new Line(1,2,3,4);
		Line l2 = new Line(4,3,2,1);
		
		/**
		 * Point class
		 */
		System.out.println("~~~~Point class~~~~");
		System.out.println(p1);
		System.out.println(p2);
		
		/**
		 * Getter for the point Class
		 */
		System.out.println("Getter");
		int x1 = p1.getX();
		int y1 = p1.getY();
		int x2 = p2.getX();
		int y2 = p2.getY();
		System.out.println("p1 = (" + x1 + "," + y1 +")");
		System.out.println("p2 = (" + x2 + "," + y2 +")");
		
		/**
		 * Equals method for Point class
		 */
		System.out.println();
		System.out.println("Equals Method");
		System.out.println("Does p1 equals p2?");
		System.out.println(p1.equals(p2));
		
		/**
		 * Line class
		 */
		System.out.println();
		System.out.println("~~~~Line class~~~~");
		System.out.println("Line 1 = " + l1);
		System.out.println("Line 2 = " + l2);
		/**
		 * Getter for the Line class
		 */
		Point l1p1 = new Point();
		Point l1p2 = new Point();
		Point l2p1 = new Point();
		Point l2p2 = new Point();
		l1p1 = l1.getP1();
		l1p2 = l1.getP2();
		l2p1 = l2.getP1();
		l2p2 = l2.getP2();
		System.out.println("l1p1 = " + l1p1);
		System.out.println("l1p2 = " + l1p2);
		System.out.println("l2p1 = " + l2p1);
		System.out.println("l2p2 = " + l2p2);
		
		/**
		 * getSlope for the Line class
		 */
		System.out.println();
		System.out.println("The slope for line 1 = " + l1.getSlope());
		System.out.println("The slope for line 2 = " + l2.getSlope());
		
		/**
		 * getDistance for the Line class
		 */
		
		System.out.println();
		System.out.println("getDistance");
		System.out.println("The distance between the two points for line one is: " + l1.getDistance());
		
		/**
		 * getMidpoint for the Line class
		 */
		System.out.println();
		System.out.println("getMidpoint");
		System.out.println("The midpoint for line one is: " + l1.getMidpoint());
		
		/**
		 * parallelTo for Line class
		 */
		
		System.out.println();
		System.out.println("parallelTo");
		System.out.println("Is line 1 parallel to line 2?");
		System.out.println(l1.parallelTo(l2));
		
		/**
		 * equals for the Line class
		 */
		
		System.out.println();
		System.out.println("equals");
		System.out.println("Is line 1 equals to line 2?");
		System.out.println(l1.equals(l2));
	}

}
