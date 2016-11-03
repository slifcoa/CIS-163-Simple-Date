package project1;

import project1.FileChooser;

public class SimpleDateDriver3
{
	public static void main( String[ ] args )
	{	
		SimpleDate date = new SimpleDate( 12, 31, 2015 ); 

		System.out.println( "Date: " + date.toString( ) );

		System.out.println( "Date ordinal date " + date.ordinalDate( ) );
		System.out.println( "30  days after " + date.toString() + ":  " + date.dateFromNow( 30 ).toString() );
		System.out.println( "365 days after " + date.toString() + ":  " + date.dateFromNow( 365 ).toString() );

		date.increment( );
		System.out.println( "Day after date " + date.toString( ) );

	FileChooser textFile;
	String fileName;
 
	textFile = new FileChooser( );
	fileName   = textFile.chooseFile( "save", "Project1" );
	date.write( fileName );

	date.read( fileName );

	System.out.println( "Date: " + date.toString( ) );	
	}
}
