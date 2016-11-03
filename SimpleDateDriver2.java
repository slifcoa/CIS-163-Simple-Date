package project1;


public class SimpleDateDriver2 
{

	public static void main( String[ ] args )
	{	
		System.out.println( "Date: " + "2/29/2016" );

		SimpleDate date = new SimpleDate( "2/29/2016" );

		System.out.println( "Date: " + date.toString( ) );
		
		System.out.println(  date.getYear() + " is a leap year?  " + date.isLeapYear( ) );
	}
}
