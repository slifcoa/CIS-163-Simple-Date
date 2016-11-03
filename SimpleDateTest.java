package project1;



import org.junit.Assert;
import org.junit.Test;

public class SimpleDateTest {

	@Test
	public void getMethods () {
		SimpleDate d = new SimpleDate("1/11/2000");

		Assert.assertEquals(11, d.getDay());
		Assert.assertEquals(1, d.getMonth());
		Assert.assertEquals(2000, d.getYear());

		d = new SimpleDate(1,11,2000);
		Assert.assertEquals(11, d.getDay());
		Assert.assertEquals(1, d.getMonth());
		Assert.assertEquals(2000, d.getYear());
	}

	@Test public void DayInYearTest() {
		SimpleDate d = new SimpleDate(1,11,1900);
		Assert.assertEquals(365, d.daysInYear());
		d = new SimpleDate(1,11,2004);
		Assert.assertEquals(366, d.daysInYear());
		d = new SimpleDate(1,11,2001);
		Assert.assertEquals(365, d.daysInYear());	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorDetectsMonthTooLow() {
		new SimpleDate("0/7/1980");
	}
	
	@Test
	public void January1stIsDay1() {
		SimpleDate a = new SimpleDate("1/1/2000");
		Assert.assertEquals(1, a.ordinalDate());
		a = new SimpleDate("3/1/2001");
		Assert.assertEquals(60, a.ordinalDate());
	}
	

	
	@Test
	public void test() {
		

		verifyValidDateString("1/1/2007", 1, 1, 2007);
		verifyValidDateString("01/01/2007", 1, 1, 2007);

	}

	private void verifyValidDateString(String s, int month, int day, int year) {
		SimpleDate date = new SimpleDate(s);

		Assert.assertEquals("Problem with day when creating " + s, day, date
				.getDay());
		Assert.assertEquals("Problem with month when creating " + s, month,
				date.getMonth());
		Assert.assertEquals("Problem with year when creating " + s, year, date
				.getYear());
	}




	@Test(expected = IllegalArgumentException.class)
	public void constructorDetectsMonthTooHigh() {
		new SimpleDate("13/1/1980");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorHandlesDateWithUnparsableField() {
		new SimpleDate("hiMom!");
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorHandlesDateWithMissingField() {
		new SimpleDate("1/2");
	}


	//
	// Test the daysInYear instance method
	//

	@Test
	public void leapYearHas366Days_instance() {
		Assert.assertEquals(366, new SimpleDate("1/1/1980").daysInYear());
	}

	@Test
	public void nonLeapYearHas365Days_instance() throws Throwable {
		Assert.assertEquals(365, new SimpleDate("1/1/1981").daysInYear());
	}

	//
	// Test the equals method for ISimpleDate
	//
	private SimpleDate e1a = new SimpleDate("1/1/2000");
	private SimpleDate e1b = new SimpleDate("1/1/2000");
	private SimpleDate e2 = new SimpleDate("1/2/2000");

	@Test
	public void equalsIsReflexive() {
		Assert.assertTrue(e1a.equals(e1a));
	}

	@Test
	public void datesWithSameInstanceDataAreEqual() throws Throwable {
		Assert.assertTrue(e1a.equals(e1b));
	}

	@Test
	public void datesWithDifferentDayAreNotEqual() throws Throwable {
		Assert.assertTrue(!e1a.equals(e2));
	}

	@Test
	public void compareToReturns0ForEqualDates() {
		Assert.assertEquals(0, e1a.compareTo(e1b));
	}

	@Test
	public void compareToReturnsMinus1IfParameterComesBeforeDate() {
		Assert.assertEquals(-1, e1a.compareTo(e2));
	}

	@Test
	public void compareToReturns1IfParameterComesAfterDate() throws Throwable {
		Assert.assertEquals(1, e2.compareTo(e1a));
	}

	//
	// Test the ordnialDate method
	//

	@Test
	public void January2IsDay2() throws Throwable {
		SimpleDate b = new SimpleDate("1/2/2001");
		Assert.assertEquals(2, b.ordinalDate());
	}
	
	@Test
	public void TestFebruary() throws Throwable {
		SimpleDate b = new SimpleDate("3/2/2000");
		Assert.assertEquals(62, b.ordinalDate());
	}

	@Test
	public void December31isDay365inNonLeapYear() throws Throwable {
		SimpleDate c = new SimpleDate("12/31/2001");
		Assert.assertEquals(365, c.ordinalDate());
	}

	//
	// tests daysFromNow
	//

	SimpleDate d1 = new SimpleDate(1,1,2001);
	SimpleDate d2 = new SimpleDate(1,2,2001);
	SimpleDate d3 = new SimpleDate(2,1,2001);
	SimpleDate d4 = new SimpleDate(12,31,2002);
	SimpleDate d5 = new SimpleDate(9,27,2003);

	@Test
	public void oneDayFromNowIsTomorrow() throws Throwable {
		Assert.assertEquals(d2, d1.dateFromNow(1));
	}

	@Test
	public void minusOneDayFromNowIsYesterday() throws Throwable {
		Assert.assertEquals(d1, d2.dateFromNow(-1));
	}

	@Test
	public void fromNow1() throws Throwable {
		System.out.println ("1 "+ d3.dateFromNow(-31));
		Assert.assertEquals(d1, d3.dateFromNow(-31));
		
	}

	@Test
	public void fromNow2() throws Throwable {
		System.out.println ("2 "+ d1.dateFromNow(31));
		Assert.assertEquals(d3, d1.dateFromNow(31));
	}


	@Test
	public void fromNow3() throws Throwable {
		System.out.println ("3 "+ d4.dateFromNow(-729));
		Assert.assertEquals(d1, d4.dateFromNow(-729));
	}


	@Test
	public void fromNow4() throws Throwable {
		System.out.println ("4 "+ d4.dateFromNow(729));
		Assert.assertEquals(d4, d1.dateFromNow(729));
	}

	
	@Test
	public void fromNow5() throws Throwable {
		d5 = new SimpleDate(9,27,2003);
		d5.dateFromNow(-1000);
		System.out.println ("5 "+ d5.dateFromNow(-1000));
		Assert.assertEquals(d1.dateFromNow(-1), d5.dateFromNow(-1000));
	}


	@Test
	public void fromNow6() throws Throwable {
		System.out.println ("6 "+ d1.dateFromNow(999));
		Assert.assertEquals(d5, d1.dateFromNow(999));
	}
	
	@Test
	public void fromNow7() throws Throwable {
		System.out.println ("6 "+ d1.dateFromNow(-1));
		System.out.println ("6 "+ d1.dateFromNow(-365));
		
		Assert.assertEquals(d2, d1.dateFromNow(1));
		Assert.assertEquals(d1, d2.dateFromNow(-1));
	}
	
	//
	// test daysAgo
	//

	@Test
	public void oneDayAgoIsYesterday() throws Throwable {
		Assert.assertEquals(d1, d2.dateBefore(1));
	}

	@Test
	public void minusOneDayAgoIsTomorrow() throws Throwable {
//		Assert.assertEquals(d2, d1.daysAgo(-1));
		Assert.assertEquals(d1, d2.dateBefore(1));
	}


}
