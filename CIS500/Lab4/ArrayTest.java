public class ArrayTest
{
	private String word;
	private char[] A;
	private char[] B;

	/**
	Initialize word with parameter key and create an array 
	of 26 elements for A and also for B
	@param key a String
	*/
	public ArrayTest(String key){
		this.word = key;
		A = new char[26];
		B = new char[26];
	}

	/**
	Initialize array A with letters 'A' through 'Z'
	*/
	public void InitArrayA(){
		int n = 0;
		for(int i = 0; i < 26; i++){
			A[i] = (char) (65 + (n++));
		}
	}

	/**
	Check and see if letter ch can be found among the first 
	num elements of array B.
	@param ch  the target letter
	@param num  the number of elements to search
	Return 1 if ch is found, otherwise -1.
	*/
	public boolean foundInB(char ch, int num){
		for(int i = 0; i < num; i++){
			if(this.B[i] == ch)
				return true;
		}
		return false;
	}

	/**
	Initialize array B, first with letters in instance variable 
	word then with letters 'Z' through 'A'. Note that there  
	are no duplicate letters in B; that is, ignore the letter 
	under consideration if it is already in the array.   
	*/
	public void InitArrayB()
	{
		int pos = 0;     // the position in which the next 
						 // letter is stored
		// loop through each letter in instance variable      
		// word and store it in array B if it is not there  	
		for(int i = 0; i < word.length(); i++){
			if(this.foundInB(word.charAt(i), i) == false){
				B[pos] = word.charAt(i);
				pos++;
			}
		}
		// loop through each letter from 'Z' to 'A' and store       
		// it in array B if it is not there already 
		for(int x = 90; x > 64; x--){
			if(this.foundInB((char)x, pos) == false){
				B[pos] = (char)x;
				pos++;
			}
		}
	}
 
	/**
	Return a string representation of an object of this class
	@Override
	*/
	public String toString()
	{
		int i;
		String s = word + "\n";
		for (i = 0; i < A.length; i++)
			s += A[i] + " ";
		s += "\n";
		for (i = 0; i < B.length; i++)
			s += B[i] + " ";
		return s;
	}
}

