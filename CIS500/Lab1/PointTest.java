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
		assertEquals(p.toString(),"(0,0)");
	}
	@Test
	public void toStringTest2(){
		Point p = new Point(5,10);
		assertEquals(p.toString(),"(5,10)");
	}
	@Test
	public void toStringTest3(){
		Point p = new Point(3,12);
		Point q = new Point(p);
		assertEquals(q.toString(),"(3,12)");
	}
	@Test
	public void equalsTest1(){
		Point p = new Point();
		assertEquals(p.equals(null),false);
	}
	@Test
	public void equalsTest2(){
		Point p = new Point(5,10);
		Point q = new Point(5,10);
		assertEquals(p.equals(q),true);
	}
	@Test
	public void equalsTest3(){
		Point p = new Point(5,10);
		Point q = new Point(10,5);
		assertEquals(p.equals(q),false);
	}
	
}
