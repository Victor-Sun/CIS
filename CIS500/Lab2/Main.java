import java.util.Scanner;

public class Main {
	public static void main( String[] args ){
		Day currentDate = new Day(2016,9,15);
		Day pastBirthdate = new Day(2015,12,10);
		
		//1
		System.out.println("1)");
		System.out.println(currentDate.daysFrom(pastBirthdate));
		
		//2
		System.out.println("2)");
		System.out.println(currentDate.addDays(100));
		
		//3
		System.out.println("3)");
		int n,y,m,date;
		Scanner input = new Scanner(System.in);
		System.out.println("Input the year: ");
		y = input.nextInt();
		System.out.println("Input the month: ");
		m = input.nextInt();
		System.out.println("Input the day: ");
		date = input.nextInt();
		System.out.println("Input the amount of days to add: ");
		n = input.nextInt();
		Day d = new Day(y,m,date);
		Day theDay = new Day(y,m,date);
		System.out.println(d.addDays(n));
		
		//4
		System.out.println("4)");
		System.out.println(theDay.daysFrom(d));
		
		//5
		System.out.println("5)");
		
	}
}