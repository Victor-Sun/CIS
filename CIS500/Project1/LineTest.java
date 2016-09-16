import static org.junit.Assert.*;
import org.junit.Test;

public class LineTest {

	@Test
	public void constructorTest1() {
		Line l = new Line();
		assertEquals(l.getP1().getX(),0);
		assertEquals(l.getP1().getX(),0);
		assertEquals(l.getP2().getY(),0);
		assertEquals(l.getP2().getY(),0);
	}

	@Test
	public void constructorTest2(){
		Line l = new Line(2,2,4,4);
		assertEquals(l.getP1().getX(),2);
		assertEquals(l.getP1().getX(),2);
		assertEquals(l.getP2().getY(),4);
		assertEquals(l.getP2().getY(),4);		
	}
	
	@Test
	public void constructorTest3(){
		Line l1 = new Line(1,2,3,4);
		Line l2 = new Line(l1);
		assertEquals(l2.getP1().getX(),1);
		assertEquals(l2.getP1().getY(),2);
		assertEquals(l2.getP2().getX(),3);
		assertEquals(l2.getP2().getY(),4);
	}
	
	@Test
	public void toStringTest1(){
		Line l = new Line();
		assertEquals(l.toString(),"[(0,0),(0,0)]");
	}
	
	@Test
	public void toStringTest2(){
		Line l = new Line(1,2,3,4);
		assertEquals(l.toString(),"[(1,2),(3,4)]");
	}
	
	@Test
	public void toStringTest3(){
		Line l1 = new Line(1,2,3,4);
		Line l2 = new Line(l1);
		assertEquals(l2.toString(),"[(1,2),(3,4)]");
	}
	
	@Test
	public void equalsTo1(){
		Line l = new Line();
		assertEquals(l.equals(null),false);
	}
	
	@Test
	public void equalsTo2(){
		Line l1 = new Line();
		Line l2 = new Line(l1);
		assertEquals(l1.equals(l2),true);
	}
	
	@Test
	public void equalsTo3(){
		Line l1 = new Line(1,2,3,4);
		Line l2 = new Line(5,6,7,8);
		assertEquals(l1.equals(l2),false);
	}
	
	@Test
	public void getSlope1(){
		Line l = new Line();
		assertEquals(,);
	}
}
