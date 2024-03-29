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
		Point p = new Point("5,10");
		Point q = new Point(p);
		assertEquals(q.getX(),5);
		assertEquals(q.getY(),10);
	}
	@Test(expected = IllegalArgumentException.class)
	public void contructorTest4() {
		new Point("5");
	}
	@Test
	public void contructorTest5() {
		Point p = new Point("5,10");
		Point q = new Point(p);
		assertEquals(q.getX(),5);
		assertEquals(q.getY(),10);
	}
	@Test(expected = IllegalArgumentException.class)
	public void contructorTest6() {
		Point p = null;
		new Point(p);
	}
	@Test
	public void toStringTest1(){
		Point p = new Point(5,10);
		assertEquals(p.toString(),"(5,10)");
	}
	@Test
	public void toStringTest2(){
		String str = "3,7";
		Point p = new Point(str);
		assertEquals(p.toString(),"(3,7)");
	}
	@Test
	public void toStringTest3(){
		Point p = new Point(5,10);
		Point q = new Point(p);
		assertEquals(q.toString(),"(5,10)");
	}
	@Test
	public void equalsTest1(){
		Point p = new Point(5,10);
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
	@Test
	public void manhattanDistance1(){
		Point p = new Point(10,15);
		assertEquals(p.manhattanDistance(p),0);
	}
	@Test
	public void manhattanDistance2(){
		Point p = new Point(5,7);
		Point q = new Point(10,15);
		assertEquals(p.manhattanDistance(q),13);
	}
	@Test
	public void manhattanDistance3(){
		Point p = new Point(5,7);
		Point q = new Point(0,0);
		assertEquals(p.manhattanDistance(q),12);
	}
	@Test
	public void manhattanDistance4(){
		Point p = new Point(5,7);
		Point q = new Point(0,-5);
		assertEquals(p.manhattanDistance(q),17);
	}
}
