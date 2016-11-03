package project1;
import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author ferguson
 *
 * Testing SimpleDate;
 */

public class SimpleDateTest1 {
	 @Test
	    public void testIsLeapYear()  {
	        SimpleDate d = new SimpleDate("3/1/2013");
	        assertFalse(d.isLeapYear());
	        d  =  new SimpleDate("3/1/2000");
	        assertTrue(d.isLeapYear());
	    }

	    @Test
	    public void testToString()  {
	        SimpleDate d = new SimpleDate("1/3/2013");
	        assertEquals("03 January 2013",d.toString());

	   //     d =  new SimpleDate("8/21/2013");
	   //     assertEquals("21 August 2013",d.toString());
	    }

	    @Test
	    public void testCompareTo1()  {
	        SimpleDate d1 = new SimpleDate("3/1/2013");
	        SimpleDate d2 = new SimpleDate("8/21/2000");
	        assertTrue(d1.compareTo(d2) > 0);

	    }
	    
	    @Test
	    public void testCompareTo2()  {
	        SimpleDate d1 = new SimpleDate("3/1/2013");
	        SimpleDate d2 = new SimpleDate("8/21/2000");
	        assertTrue(d2.compareTo(d1) < 0);
	    }

	    // must use separate
	    @Test (expected = IllegalArgumentException.class)
	    public void testIncorrectYear() {
	        SimpleDate d1 = new SimpleDate("3/1/1700");
	//         d1 = new SimpleDate("1/1/1700");  // not reached.

	    }


}
