import java.util.Iterator;
import java.util.Map;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A short project that scans through a source input and
 * takes all the identifiers and stores them in a map
 * 
 * @author Victor Sun
 */
public class Proj3 {
	Map<String, Set<Integer>> Identifier; // Map for Identifiers
	Scanner in = new Scanner(System.in);
	
	/**
	 * Constructor for the Proj3 class
	 */
	public Proj3(){
		Identifier = new TreeMap<String, Set<Integer>>();
	}

	/**
	 * Parses the input into words to be inserted into a Map
	 * 
	 * @param in The input for the text to be parsed for the Map
	 * @throws IOException
	 */
	public void parseText (Scanner in)throws IOException{  
		String token = "";
		int lineNo = 0,state = 0;
		
		while (in.hasNextLine()){
			lineNo++;
			String line = in.nextLine();
			char[] list = line.toCharArray();
			if(line.startsWith("//")){
				continue;
			}
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
					} else if(ch == '/'){
						token = "";
						state = 3;
						continue;
					} else if(ch == '"'){
						token = "";
						state = 4;
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
						updateCounts(token, lineNo);
						token = "";
						state = 0;
						continue;
					} else if(ch == '/'){
						updateCounts(token, lineNo);
						token = "";
						state = 3;
						continue;
					} else if(ch == '"'){
						token = "";
						state = 4;
						continue;
					} else {
						updateCounts(token, lineNo);
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
					} else if(ch == '/'){
						token = "";
						state = 3;
						continue;
					} else if(ch == '"'){
						token = "";
						state = 4;
						continue;
					} else {
						token = "";
						state = 2;
						continue;
					}
				}
				if(state == 3){
					if(Character.isLetterOrDigit(ch) || ch == '_'){
						token += ch;
						state = 1;
						continue;
					} else if(Character.isWhitespace(ch)){
						token = "";
						state = 0;
						continue;
					} else if(ch == '/'){
						token = "";
						state = 3;
						break;
					} else if(ch == '"'){
						token = "";
						state = 4;
						continue;
					} else {
						token = "";
						state = 2;
						continue;
					}
				}
				if(state == 4){
					if(ch == '"'){
						token = "";
						state = 2;
						continue;
					} else {
						token = "";
						state = 4;
						continue;
					}
				}
			}
		}
		System.out.println("Number of lines is " + lineNo);
		in.close(); 
	}

	/**
	 * Displays the identifier and the line number(s) that the identifier can be found in
	 */
	public void displayIdentifiers (){
		Set set = Identifier.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.println("Identifier: " + me.getKey() + " \t & Line Number(s): " + me.getValue());
		}
	}

	/**
	 * Adds the line number to the Map for a key if the key does not exist 
	 * in the Map, or adds the line number to the existing value for the key in the Map
	 * 
	 * @param token The Identifier being added to the Map
	 * @param line The line number for the Identifier being added to the Map
	 */
	public void updateCounts (String token, int line){
		Set<Integer> lineNumber = new TreeSet<Integer>(); // Line number for Identifier
		if(Identifier.containsKey(token)){
			lineNumber = Identifier.get(token);
			lineNumber.add(line);
			Identifier.put(token, lineNumber);
		} else {
			lineNumber.add(line);
			Identifier.put(token, lineNumber);
		}
	}
}
