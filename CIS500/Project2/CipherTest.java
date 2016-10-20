import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CipherTest {

	public static void main(String args[]){
		try{
			String keyword = "";
			Scanner in = new Scanner(System.in);

			
			System.out.print("Select an option: 1 to encrypt, 2 to decrypt or 0 to quit");
			int input = in.nextInt();
			
			if(input == 1 ){
				System.out.println("Input encryption keyword: ");
				keyword = in.next();
				InputStream inStream = new FileInputStream("data.txt");
				OutputStream outEStream = new FileOutputStream("data.encrypted");

				// Encryption Process
				Cipher cipher = new Cipher(keyword);
				cipher.keyConvert();
				cipher.encryptStream(inStream, outEStream);
				inStream.close();
				outEStream.close();
			} else if(input == 2){
				System.out.println("Input decryption keyword: ");
				keyword = in.next();
				InputStream inStream = new FileInputStream("data.encrypted");
				OutputStream outDStream = new FileOutputStream("data.decrypted");
				
				// Decryption Process
				Cipher cipher = new Cipher(keyword);
				cipher.keyConvert();
				cipher.decryptStream(inStream, outDStream);
				inStream.close();
				outDStream.close();
				
			} else if(input == 0){
				System.out.println("Option 0 selected...");
				System.out.println("Quitting...");
				System.exit(0);
			}
		} catch(InputMismatchException e){
			System.out.println("Invalid Input...");
			System.out.println("Quitting...");
		} catch(IOException e){
			System.out.println("Error processing file: " + e);
		}
	}
}