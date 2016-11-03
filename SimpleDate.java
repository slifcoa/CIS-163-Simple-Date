//By : Adam Slifco
package project1;

import java.util.Scanner;
import java.io.*;

public class SimpleDate {
	// **********************************************************
	// fields

	private int day;
	private int month;
	private int year;

	// **********************************************************
	// static class variable

	private static int simpleDates = 0;

	// **********************************************************
	// static class constants

	// A reference on Java arrays:
	// http://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html

	private static final int[] DAYS_IN_MONTH = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final int[] LEAP_DAYS_IN_MONTH = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	// private static final int[] [] END_OF_MONTH = { DAYS_IN_MONTH,
	// LEAP_DAYS_IN_MONTH };

	private static final int[] DAYS_BEFORE = { 0, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
	private static final int[] LEAP_DAYS_BEFORE = { 0, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366 };
	// private static final int[] [] BEFORE = { DAYS_BEFORE, LEAP_DAYS_BEFORE };

	private static final String[] MONTH = { "*", "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };

	public static final int MIN_YEAR = 1753;
	public static final SimpleDate MINIMUM_DATE = new SimpleDate(1, 1, 1753);

	// **********************************************************
	// Constructor passes three integer values for a new object
	public SimpleDate(int month, int day, int year) {
		this.day = day;
		this.month = month;
		this.year = year;

		validateCount();
	}

	// **********************************************************
	// passes a String for a new object
	public SimpleDate(String startDate) {

		this.setDate(startDate);

		validateCount();
	}

	// **********************************************************
	// passes an object for the new object
	public SimpleDate(SimpleDate other) {
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;

		validateCount();
	}

	// **********************************************************
	// checks to see if the object is with-in values that are logically valid.
	private boolean isValidDate() {
		int daysInMonth;

		if (this.month < 1 || this.month > 12) {
			return false;

		}
		if (this.isLeapYear()) {
			daysInMonth = LEAP_DAYS_IN_MONTH[this.month];
		} else {
			daysInMonth = DAYS_IN_MONTH[this.month];
		}
		if (this.day < 1 || this.day > daysInMonth) {
			return false;
		}
		if (this.year < MIN_YEAR) {
			return false;
		} else {
			return true;
		}
	}

	// **********************************************************
	// validates an object and counts the number of objects created.
	private void validateCount() {

		if (this.isValidDate()) {
			simpleDates++;

		} else {
			throw new IllegalArgumentException(
					String.format("%d/%d/%d", month, day, year) + " does not represent a valid date");
		}
	}

	// **********************************************************
	// split's a string into three integers
	private void setDate(String stringDate) {
		String[] date = stringDate.split("/|-");

		if (date.length != 3) {
			throw new IllegalArgumentException(stringDate + " does not represent a valid date.");
		}
		try {
			this.day = Integer.parseInt(date[1]);
			this.month = Integer.parseInt(date[0]);
			this.year = Integer.parseInt(date[2]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(stringDate + " does not represent a valid date.");

		}
	}

	// **********************************************************
	// checks to see if the year is a leap year
	public boolean isLeapYear() {
		if ((this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)) {
			return true;
		} else {
			return false;

		}
	}

	// **********************************************************
	// returns how many days into the year the date is
	public int ordinalDate() {
		if (this.isLeapYear()) {
			return LEAP_DAYS_BEFORE[this.month] + this.day;
		} else {
			return DAYS_BEFORE[this.month] + this.day;
		}
	}

	// **********************************************************
	// advances to the next calendar day
	public void increment() {
		if (this.isLeapYear()) {
			if (this.day < LEAP_DAYS_IN_MONTH[this.month]) {
				this.day++;
			} else {
				this.day = 1;
				this.month++;

				if (this.month > 12) {
					this.month = 1;
					this.year++;
				}
			}
		} else {
			if (this.day < DAYS_IN_MONTH[this.month]) {
				this.day++;
			} else {
				this.day = 1;
				this.month++;

				if (this.month > 12) {
					this.month = 1;
					this.year++;
				}
			}
		}
	}

	// **********************************************************
	// jump's back to the previous calendar day
	public void decrement() {
		if (this.equals(SimpleDate.MINIMUM_DATE)) {
			throw new IllegalArgumentException("The day before " + this.toString() + " does not exist.");
		}

		this.day--;
		if (this.day == 0) {
			this.month--;

			if (this.month == 0) {
				this.month = 12;
				this.year--;
			}

			if (this.isLeapYear()) {
				this.day = LEAP_DAYS_IN_MONTH[this.month];
			} else {
				this.day = DAYS_IN_MONTH[this.month];
			}
		}
	}

	// **********************************************************
	// return's the date in proper format
	public String toString() {
		String helper = "0" + this.day;

		return helper.substring(helper.length() - 2) + " " + MONTH[this.month] + " " + this.year;
	}

	// **********************************************************
	// return's date in alternative format
	public String toString2() {
		String helper = "0" + this.day;

		return this.month + "-" + helper.substring(helper.length() - 2) + "-" + this.year;

	}

	// **********************************************************
	// get's the number of object's created
	public static int getNumberOfSimpleDates() {
		return simpleDates;
	}

	// **********************************************************

	public void write(PrintWriter out) {
		out.println(this.toString2());

	}

	public void write(String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

			write(out);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// **********************************************************

	public void read(Scanner fileReader) {
		this.setDate(fileReader.nextLine());
	}

	// **********************************************************

	public void read(String fileName) {
		try {
			Scanner fileReader = new Scanner(new File(fileName));

			read(fileReader);

			fileReader.close();
		} catch (FileNotFoundException error) {
			System.out.println("File not found ");
		} catch (Exception error) {
			System.out.println("Oops!  Something went wrong.");
		}
	}

	// **********************************************************
	// returns the previous day that the parameter wants to find
	public SimpleDate dateBefore(int n) throws IllegalArgumentException {
		SimpleDate dateBefore = new SimpleDate(this);

		while (n > 0) {
			dateBefore.decrement();
			n--;
		}
		return dateBefore;
	}

	// **********************************************************
	// returns the future day the parameter wants to find
	public SimpleDate dateAfter(int n) throws IllegalArgumentException {
		SimpleDate dateAfter = new SimpleDate(this);
		while (n > 0) {
			dateAfter.increment();
			n--;
		}
		return dateAfter;
	}

	// **********************************************************
	// returns either a past date or future date depending on the value of the
	// parameter
	public SimpleDate dateFromNow(int n) throws IllegalArgumentException {
		SimpleDate date;
		if (n == 0) {
			date = new SimpleDate(this);
		} else if (n < 0) {
			return this.dateBefore(-n);
		} else {
			return this.dateAfter(n);
		}
		return date;
	}

	// **********************************************************
	// calculates how far away the passed object is from the current date
	public int daysSince(SimpleDate other) {
		int counter = 0;
		SimpleDate tempDate = new SimpleDate(other);

		if (this.compareTo(tempDate) < 0) {
			throw new IllegalArgumentException("This date has already happened");
		} else if (compareTo(tempDate) == 0) {
			counter = 0;
		} else {
			while (!tempDate.equals(this)) {
				this.decrement();
				counter++;
			}
		}
		return counter;
	}

	// **********************************************************
	public int getDay() {
		return this.day;
	}

	// **********************************************************

	public int getMonth() {
		return this.month;
	}

	// **********************************************************

	public int getYear() {
		return this.year;
	}

	// **********************************************************
	// checks to see if it is a leap year
	public int daysInYear() {
		if (isLeapYear()) {
			return 366;
		} else {
			return 365;
		}
	}

	// **********************************************************
	// checks to see if the passed date is equal to the current date
	public boolean equals(SimpleDate other) {
		if ((this.year == other.year) && (this.month == other.month) && (this.day == other.day)) {
			return true;
		} else {
			return false;
		}
	}

	// **********************************************************
	// checks to see if the passed object is a date and is equal to the current
	// date
	public boolean equals(Object other) {
		if (other instanceof SimpleDate) {
			return this.equals((SimpleDate) other);
		} else {
			return false;
		}
	}

	// **********************************************************
	// checks to see if the two passed dates are equal to eachother
	public boolean equals(SimpleDate s1, SimpleDate s2) {
		if ((s1.year == s2.year) && (s1.month == s2.month) && (s1.day == s2.day)) {
			return true;
		} else {
			return false;
		}
	}

	// **********************************************************
	// compares if the current date is either before or after the passed date
	public int compareTo(SimpleDate other) {
		if (this.year != other.year) {
			return this.year - other.year;
		} else if (this.month != other.month) {
			return this.month - other.month;
		} else {
			return this.day - other.day;
		}

	}

	// **********************************************************

	public static void main(String[] args) {
		SimpleDate date1 = new SimpleDate(3, 1, 2016);
		SimpleDate date2 = new SimpleDate("2-29-2016");

		System.out.println("Date1: " + date1.toString());
		System.out.println("Date2: " + date2.toString());
	}

}
