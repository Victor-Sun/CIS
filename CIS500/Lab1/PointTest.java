import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void contructorTest1() {
		Point p = new Point();
		assertEquals(p.getX(),0);
		assertEquals(p.getY(),0);
	}
	@Test
	public void contructorTest2() {
		Point p = new Point(5,10);
		assertEquals(p.getX(),5);
		assertEquals(p.getY(),10);
	}
	@Test
	public void contructorTest3() {
		Point p = new Point(5,10);
		Point q = new Point(p);
		assertEquals(p.getX(),5);
		assertEquals(p.getY(),10);
	}
	@Test
	public void toStringTest1(){
		Point p = new Point();
		p.toString();
		assertEquals(p.toString(),"(" + p.getX() + "," + p.getY() + ")");
	}
}
