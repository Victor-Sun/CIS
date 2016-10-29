import java.util.Arrays;
import java.util.Random;

/**
	This program demonstrates the selection sort algorithm by
	sorting an array that is filled with random numbers.
*/
public class SelectionSortDemo
{  
	public static void main(String[] args)
	{  
		Random generator = new Random();  
		int length = 10, c;
		Coin[] a = new Coin[length];  
		
		
		for (int i = 0; i < a.length; i++){
			 c = generator.nextInt(4);
			 switch(c){
				case 0:
				    a[i] = new Coin(1,"Penny");
				    continue;
				case 1:
					a[i] = new Coin(5,"Nickel");
					continue;
				case 2:
					a[i] = new Coin(10,"Dime");
					continue;
				case 3:
					a[i] = new Coin(25,"Quarter");
					continue;
			 }
		}
		
		System.out.println("Unsorted List: " + Arrays.toString(a));

		SelectionSorter sorter = new SelectionSorter(a);
		sorter.sort();

		System.out.println("Sorted List:   " + Arrays.toString(a));
	}
}