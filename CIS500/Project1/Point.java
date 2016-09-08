public class Point {

	private int x;
	private int y;
	
	/**
	 * Initializes a newly created Point object with x and y
	 * coordinates set to 0.
	 */
	public Point() {

	}
	
	/**
	 * Initializes a newly created Point object with the given 
	 * values.
	 * 
	 * @param x the x coordinate of this point
	 * @param y the y coordinate of this point
	 */
	public Point(int x, int y) {

	}
	
	/**
	 * Initializes a newly created Point object with the values 
	 * from the input string.
	 * 
	 * @param str string containing values of coordinates
	 */
	public Point(String str) {

	}
	
	/**
	 * Initializes a newly created Point object with the values 
	 * from the input Point object.
	 * 
	 * @param other a Point object used to initialize this Point 
	 * object
	 */
	public Point(Point other) {

	}
	
	/**
	 * Returns the x coordinate of this Point object.
	 * 
	 * @return the x coordinate of this object.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of this Point object.
	 * 
	 * @return the y coordinate of this object.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns a String object that represents this Point as, 
	 * for example, (5, 3) if x is 5 and y is 3.
	 * 
	 * @return a string representation of this Point's value.
	 */
	public String toString() {
		// TO DO
		return null;
	}
	
	/**
	 * Compares this object to the other object. The result is 
	 * true if and only if the argument is not null and is a 
	 * Point object that contains the same values as this Point 
	 * object.
	 * 
	 * @param obj the object to compare with.
	 * 
	 * @return true if the objects are the same; false 
	 * otherwise.
	 */
	public boolean equals(Object other) {
		// TO DO
		return false;
	}
	
	/**
	 * Returns the Manhattan distance between this Point object 
	 * and the other Point object.
	 * 
	 * Manhattan distance is the distance between two points if 
	 * you walk only in a horizontal or vertical direction.
	 * 
	 * @param other the other Point object
	 * 
	 * @return the Manhattan distance between this and other 
	 * Point objects.
	 */
	public int manhattanDistance(Point other) {
		// TO DO
		return 0;
	}
	
}