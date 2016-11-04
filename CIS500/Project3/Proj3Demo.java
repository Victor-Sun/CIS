import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/**
 * Project 3 demo class
 * 
 * @author Victor Sun
 */
public class Proj3Demo {
//TODO 1. Things after // is not identifier
	// 2. Check for why DataSet is on line 13 also
	// 3. String constant is not an identifier
	public static void main(String[] args) {
		System.out.println("CIS500 Project 3");
		Scanner in = new Scanner(System.in);
		try{
//			System.out.print("What is your input file: ");
//			String inFile = in.next();
			
			Scanner inf = new Scanner(new File("DataAnalyzer.java"));
			Proj3 P = new Proj3();
			P.parseText(inf);
			P.displayIdentifiers();
			inf.close();
			
			
		} catch (IOException exception){
			System.out.println("Error: " + exception);
		}
	}

}
