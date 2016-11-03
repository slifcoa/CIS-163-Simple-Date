package project1;

public class SimpleDateDriver1
{
	public static void main( String[ ] args )
	{	
		System.out.println( "Date: " + "new SimpleDate( \"1/2/1753\" )" );

		SimpleDate date = new SimpleDate( "3/3/2016" );

		System.out.println( "Date: " + date.toString( ) );
		
		System.out.println( "date.increment()" );
		for(int i = 1; i <= 16736455; i++){
		date.increment( );
		}
		System.out.println( "Date: " + date.toString( ) );
		
		System.out.println( "date.decrement()" );		
		date.decrement( );
		System.out.println( "Date: " + date.toString( ) );
	}
}
