public class DayTester
{
   public static void main(String[] args)
   {
      Day aDay = new Day(2012, 9, 10); 
      Day later = aDay.addDays(999);
      System.out.println(later.getYear() 
         + "-" + later.getMonth() 
         + "-" + later.getDate()); 
      System.out.println(later.daysFrom(aDay));
   }
}