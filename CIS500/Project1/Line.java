public class Line {

	private Point p1, p2;

	/**
	 * Initializes a newly created Point object with x and y coordinates set to 0.
	 */
	public Line(){
		this.p1 = new Point();
		this.p2 = new Point();
	}

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
	 * Initializes a newly created Point object with the values from the input Point object.
	 * 
	 * @param other a Point object used to initialize this Point object
	 */
	public Line(Line other){
		this.p1 = other.getP1();
		this.p2 = other.getP2();
	}
	/**
	 * Returns the point coordinate of this Line object.
	 * 
	 * @return the point p1 coordinate of this object.
	 */
	public Point getP1(){
		return this.p1;
	}

	/**
	 * Returns the point coordinate of this Line object.
	 * 
	 * @return the point p2 coordinate of this object.
	 */
	public Point getP2(){
		return this.p2;
	}

	/**
	 * Returns the y coordinate of this Point object.
	 * 
	 * @return the y coordinate of this object.
	 */

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
		double slope = 0;
		if(this.p1.getX() == this.p2.getX()){
			ArithmeticException e = new ArithmeticException();
			throw e;
		}
		else {
			slope = (p1.getY() - p2.getY())/(p1.getX() - p2.getX());
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
		// Need to redo, incorrect formula
		d1 = p1.getX()+p1.getY();
		d2 = p2.getX()+p2.getX();
		distance = Math.abs(d1-d2);

		return distance;
	}

	/**
	 * Calculate the middle point of this Line object 
	 * 
	 * @return a Point object
	 */
	public Point getMidpoint() {
		Point midpoint;
		int midX,midY;

		midX = (this.p1.getX() + this.p2.getX()) / 2;
		midY = (this.p1.getY() + this.p2.getY()) / 2;

		midpoint = new Point(midX,midY);
		return midpoint;
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
		if (this.getSlope() == line.getSlope()){
			return true;
		}
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
		if (obj == null || !(obj instanceof Line)){
			return false;
		}
		Line l = (Line) obj;
		if(this.p1.getX() == l.p1.getX() 
				&& this.p1.getY() == l.p1.getY()
				&& this.p2.getX() == l.p2.getX() 
				&& this.p2.getY() == l.p2.getY()){
			return true;
		}
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