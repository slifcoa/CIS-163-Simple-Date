package project1;

import project1.FileChooser;

public class SimpleDateDriver4 
{
	public static void main( String[ ] args )
	{	
		SimpleDate date1 = new SimpleDate( 3,1,2013 );
		SimpleDate date2 = new SimpleDate( "2-29-2000" );

		System.out.println( "Date1: " + date1.toString( ) );
		System.out.println( "Date2: " + date2.toString( ) );

		FileChooser textFile;
		String fileName;

		textFile = new FileChooser( );
		fileName   = textFile.chooseFile( "save", "Project1" );

		date1.write( fileName );  //for example,  "date.txt" );

		date1.read( fileName );   //for example,  "date1.txt");

		System.out.println( "Date1: " + date1.toString( ) );	
	}
}
