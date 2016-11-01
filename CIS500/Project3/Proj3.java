import java.io.IOException;
import java.util.Scanner;

import com.sun.javafx.collections.MappingChange.Map;

public class Proj3 {
	// TODO Change parseText method to check for identifier 
	// Get rid of all updateCounts() method to accommodate for inserting the identifiers and their line numbers into the map
	// Use map to to maintain an ordered collection for identifier 
	
	Map<String, Integer> Identifier;
	
	
	
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
	
	public static void main(String[] args) {
		//TODO Complete project 3
	}
}
