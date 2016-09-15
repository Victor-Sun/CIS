public class Line {

	private Point p1, p2;

	/**
	 * Initializes a newly created Line object with the given 
	 * values.
	 * 
	 * @param x1 and x2 the x coordinates of p1 and p2 
	 * @param y1 and y2 the y coordinates of p1 and p2 
	 */
	public Line(int x1, int y1, int x2, int y2) {
		this.p1 = new Point(x1,y1);
		this.p2 = new Point(x2,y2);
	}

	/**
	 * Initializes a newly created Line object with the values 
	 * from the two input Point objects.
	 * 
	 * @param p1 and p2 two Point objects used to initialize 
	 * this Line object
	 */
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Calculate the slope of this Line object using the  
	 * formula (y1 - y2)/(x1 - x2)
	 * 
	 * slope of a vertical line is undefined, that is, x1 and x2  
	 * are equal, throw an ArithmeticException 
	 *  
	 * @return the slope of this Line object
	 */
	public double getSlope() {
		int slope = 0;
		slope = (p1.getY() - p2.getY())/(p1.getX() - p2.getX());
		if(this.p1.getX() == this.p2.getX()){
			ArithmeticException e = new ArithmeticException();
			throw e;
		}
		else {
			return slope;
		}
	}

	/**
	 * Calculate the distance between the two points of 
	 * this Line object 
	 * 
	 * @return the distance
	 */
	public double getDistance() {
		int d1, d2, distance;
		d1 = p1.getX()+p1.getY();
		d2 = p2.getX()+p2.getX();
		
		distance = d1-d2;
		
		return distance;
	}

	/**
	 * Calculate the middle point of this Line object 
	 * 
	 * @return a Point object
	 */
	public Point getMidpoint() {
		// TO DO
		return null;
	}
	
	/**
	 * two lines are parallel if they have the same slope, or 
	 * if they are both vertical. Note that two slopes are the 
	 * same if their difference is very small.
	 *
	 * @param line the other Line object
	 *
	 * @return true if the objects are parallel; false
	 * otherwise.  
	 */ 
	public boolean parallelTo(Line line) {
		// TO DO
		return false;
	}

	/**
	 * Compares this object to the other object. The result is 
	 * true if and only if the argument is not null and is a 
	 * Line object with the same values as this Line object
	 * 
	 * @param obj the object to compare with.
	 * 
	 * @return true if the objects are the same; false 
	 * otherwise.
	 */
	public boolean equals(Object obj) {
		// TO DO
		return false;
	}
	
	/**
	 * Returns a String object that represents this Line 
	 * 
	 * @return a string representation of this Line's value.
	 */
	public String toString() {
		return "[" + p1 + "," + p2 +"]";
	}
}