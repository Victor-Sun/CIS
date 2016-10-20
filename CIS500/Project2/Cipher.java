import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Project 2
 * Name: Victor Sun
 * Class: CIS 500
 */

public class Cipher {
	
	private String key;
	private int akey, dkey;
	private char[] keyArray;
	
	/**
	 * Constructs a cipher object with the given key.
	 * @param key
	 */
	public Cipher(String key){
		this.key = key;
	}
	
	/**
	 * Converts string key into an encryption key
	 */
	public void keyConvert(){
		keyArray = this.key.toCharArray();
		for(int i = 0; i < keyArray.length; i++){
			akey += keyArray[i];
		}
		akey = akey % 26;
	}
	
	/**
    Encrypts the contents of a stream.
    @param in the input stream
    @param out the output stream
    */      
	 public void encryptStream(InputStream in, OutputStream out)
	       throws IOException
	 {
	    boolean done = false;
	    while (!done)
	    {
	       int next = in.read();
	       if (next == -1) 
	          done = true;
	       else
	       {
				char b = (char) next;
	          char c = encrypt(b);
	          out.write(c);
	       }
	    }
	 }
	
		/**
	    Decrypts the contents of a stream.
	    @param in the input stream
	    @param out the output stream
	    */  
	 public void decryptStream(InputStream in, OutputStream out)
		       throws IOException
		 {
		    boolean done = false;
		    while (!done)
		    {
		       int next = in.read();
		       if (next == -1) 
		          done = true;
		       else
		       {
					char b = (char) next;
		          char c = decrypt(b);
		          out.write(c);
		       }
		    }
		 }
	 
	/**
	 * Encrypts the string
	 * @param c
	 * @return the encrypted character
	 */
	public char encrypt(char c){
		 if ( akey < 0 )
			 akey = akey + 26;

		 if (Character.isUpperCase(c) )
			 return (char)((c - 'A' + akey) % 26 + 'A');
		
		 if (Character.isLowerCase(c) )
			 return (char)((c - 'a' + akey) % 26 + 'a');
		
		 return c;
	}
	
	/**
	 * Decrypts the string
	 * @param c
	 * @return the decrypted character
	 */
	public char decrypt(char c) {
		int result = c;
		if (Character.isUpperCase(result)) {
			result = result - (akey % 26);
			if (result < 'A'){
				result = result + 26;
			}
		} else if (Character.isLowerCase(result)) {
			result = result - (akey % 26);
			if (result < 'a'){
				result = result + 26;
			}
		}
		c = (char) result;
		return c;
	}

}