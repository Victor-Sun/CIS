public class Point {

	private int x;
	private int y;

	/**
	 * Initializes a newly created Point object with x and y coordinates set to 0.
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Initializes a newly created Point object with the given values.
	 * 
	 * @param x the x coordinate of this point
	 * @param y the y coordinate of this point
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Initializes a newly created Point object with the values 
	 * from the input string.
	 * 
	 * @param str string containing values of coordinates
	 */
	public Point(String str) throws IllegalArgumentException{
		if(str.length() < 2){
			throw new IllegalArgumentException();
		}
		String[] s = str.split(",");
		this.x = Integer.parseInt(s[0]);
		this.y = Integer.parseInt(s[1]);
	}
	
	/**
	 * Initializes a newly created Point object with the values from the input Point object.
	 * 
	 * @param other a Point object used to initialize this Point object
	 */
	public Point(Point other) throws IllegalArgumentException{
		if(other == null){
			throw new IllegalArgumentException();
		}
		this.x = other.x;
		this.y = other.y;
	}
	
	/**
	 * Returns the x coordinate of this Point object.
	 * 
	 * @return the x coordinate of this object.
	 */
	public int getX() {
		return this.x;	
	}

	/**
	 * Returns the y coordinate of this Point object.
	 * 
	 * @return the y coordinate of this object.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Returns a String object that represents this Point as, 
	 * for example, (5, 3) if x is 5 and y is 3.
	 * 
	 * @return a string representation of this Point's value.
	 */
	public String toString() {
		return "(" + this.x + "," + this.y + ")";	
	}
	
	/**
	 * Compares this object to the other object. The result is true if and
	 * only if the argument is not null and is a Point object that contains the
	 * same values as this Point object.
	 * 
	 * @param obj the object to compare with.
	 * 
	 * @return true if the objects are the same; false otherwise.
	 */
	public boolean equals(Object other) {
		if (other == null || !(other instanceof Point))
			return false;
		Point p = (Point) other;

		if(p.getX() == this.getX()){
			if(p.getY() == this.getY())
				return true;
		}
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
		int mDistance;
		//Manhattan Distance = Math.abs(x1-x2) + Math.abs(y1-y2)
		mDistance = Math.abs(this.x-other.x) + Math.abs(this.y-other.y);
		return mDistance;
	}
}