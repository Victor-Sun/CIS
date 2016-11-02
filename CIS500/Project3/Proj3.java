import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Proj3 {
	// TODO Change parseText method to check for identifier 
	// Get rid of all updateCounts() method to accommodate for inserting the identifiers and their line numbers into the map
	// Use map to to maintain an ordered collection for identifier 

	Map<String, Set<Integer>> Identifier; // Map for Identifiers
	Scanner in = new Scanner(System.in);
	
	public Proj3(){
		Identifier = new HashMap<String, Set<Integer>>();
	}

	public void parseText (Scanner in)throws IOException{  
		String token = "";
		int lineNo = 0,state = 0;
		
		while (in.hasNextLine()){
			lineNo++;
			String line = in.nextLine();
			char[] list = line.toCharArray();
			for(char ch:list){
				if(state == 0){ // Whitespace
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
				if(state == 1){ // Alphanumeric or Underscore 
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
				if(state == 2){ // Everything else
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
			System.out.println("Identifier: " + me.getKey() + " \t & Line Number: " + me.getValue());
		}
	}

	// Update the count for the identifier
	public void updateCounts (String token){
		Set<Integer> lineNumber = new HashSet<Integer>(); // Line number for Identifier
		// TODO If the identifier already contains the key add the line number to the set
		if(Identifier.containsKey(token)){
			
		} else {
		
		}
	}
}
