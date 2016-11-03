package project1;

public class SimpleDateDriver0
{
	
	public static void main( String[ ] args )
	{	
		System.out.println( "Date: " + "1/1/1753" );

		SimpleDate date = new SimpleDate( "1/1/1753" );

		System.out.println( "Date: " + date.toString( ) );
		System.out.println( "SimpleDate.MINIMUM_DATE: " + SimpleDate.MINIMUM_DATE.toString( ) );
		System.out.println( "SimpleDate.MINIMUM_DATE: " + date.equals( SimpleDate.MINIMUM_DATE ) );

		System.out.println(  date.getYear() + " is a leap year?  " + date.isLeapYear( ) );
	}
	
}
