import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import javafx.scene.shape.Line;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Proj3 {
	// TODO Change parseText method to check for identifier 
	// Get rid of all updateCounts() method to accommodate for inserting the identifiers and their line numbers into the map
	// Use map to to maintain an ordered collection for identifier 
	
	HashMap<String, Integer> Identifier;
	Scanner in = new Scanner(System.in);
	
	public Proj3(){
		Identifier = new HashMap<String, Integer>();
	}
	
	public void parseText (Scanner in)throws IOException{  
		String token = "";
		int lineNo = 0,state = 0;
		while (in.hasNextLine()){
			lineNo++;
			String line = in.nextLine();
			char[] list = line.toCharArray();
			for(char ch:list){
				if(state == 0){
					if(Character.isLetterOrDigit(ch) || ch == '_'){
						token += ch;
						state = 1;
						continue;
					} else if(Character.isWhitespace(ch)){
						token = "";
						state = 0;
						continue;
					} else {
						token = "";
						state = 2;
						continue;
					}
				}
				if(state == 1){
					if(Character.isLetterOrDigit(ch) || ch == '_'){
						token += ch;
						state = 1;
						continue;
					} else if(Character.isWhitespace(ch)){
						updateCounts(token);
						token = "";
						state = 0;
						continue;
					} else {
						updateCounts(token);
						token = "";
						state = 2;
						continue;
					}
				}
				if(state == 2){
					if(Character.isLetterOrDigit(ch) || ch == '_'){
						token += ch;
						state = 1;
						continue;
					} else if(Character.isWhitespace(ch)){
						updateCounts(token);
						token = "";
						state = 0;
						continue;
					} else {
						token = "";
						state = 2;
						continue;
					}
				}
			}
		}
			  
		System.out.println("Number of lines is " + lineNo);
		in.close(); 
	}
	
	public void displayIdentifiers (){
		Set set = Identifier.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.println("Identifier: " + me.getKey() + " \t\t & Line Number: " + me.getValue());
		}
	}
	
	public static void main(String[] args) {
		//TODO Complete project 3
		System.out.println("Project 3");
	}
}
