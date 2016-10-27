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
	  int length = 10; 
	  int[] a = new int[length];  
	  
	  for (int i = 0; i < a.length; i++)
		  a[i] = generator.nextInt(100);
	  
      System.out.println("Unsorted List: " + Arrays.toString(a));

      SelectionSorter sorter = new SelectionSorter(a);
      sorter.sort();

      System.out.println("Sorted List:   " + Arrays.toString(a));
   }
}