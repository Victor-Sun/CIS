import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import javafx.scene.shape.Line;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
	This program identifies all words and symbol sequences from text 
	in a file and keeps track of their frequency counts
*/
public class FrequencyCount{
	Map<String, Integer> frequencies;	 // holds token-count pairs
	
	public FrequencyCount () {
	  // create an object from class Map and
	  // assign it to frequencies 
		frequencies = new HashMap<String, Integer>();
	}

	public void parseText (Scanner in)throws IOException{  
		String token = "";
		int lineNo = 0,state = 0;
		while (in.hasNextLine()){
			lineNo++;
			String line = in.nextLine();
			char[] list = line.toCharArray();
			for ( char ch: list ) {
			 // process input text character by character,
			 // identify each token and update its count 
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

	// Display all words and frequency counts on the screen
	public void displayCounts (){
		Set set = frequencies.entrySet();
		Iterator i = set.iterator();
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.println("Key: " + me.getKey() + " \t\t & Value: " + me.getValue());
		}
	}

	// Check and see if the specified token is in map frequencies 
	// If not, put in this map a token-count pair where count is 1
	// otherwise, increment its frequency count and update the map
	public void updateCounts (String token){
		if(frequencies.containsKey(token)){
			frequencies.put(token, frequencies.get(token) + 1);
		} else {
			frequencies.put(token, 1);
		}
	}
}
