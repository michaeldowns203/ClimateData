//This class stores the date that is attached to each and every piece of data in this program. It creates a Date object to do this. This object stores the day, month and year of each date. This class also provides getters for each piece of the Date object, a toString method to convert each object to a readable string, and a compareTo method to compare each date within each Date object by comparing the year within each Date object, then the month if the two date objects have an equal year, then the day if the two date objects have an equal month and year.
public class Date implements Comparable<Date> {
	//instance variables
	private int day;
	private int month;
	private int year;
	
	//constructor
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	//getter for day
	public int getDay() {
		return day;
	}

	//getter for month
	public int getMonth() {
		return month;
	}

	//getter for year
	public int getYear() {
		return year;
	}

	//this method converts each Date object to a readable string formatted to the correct output for this program
	@Override
	public String toString() {
		//instance variable to store month name (stores month as a string month name rather than int month number)
		String ms = "";
		//convert int month number to string month name
		if (month == 1) {
			ms = "Jan";
		}
		if (month == 2) {
			ms = "Feb";
		}
		if (month == 3) {
			ms = "Mar";
		}
		if (month == 4) {
			ms = "Apr";
		}
		if (month == 5) {
			ms = "May";
		}
		if (month == 6) {
			ms = "Jun";
		}
		if (month == 7) {
			ms = "Jul";
		}
		if (month == 8) {
			ms = "Aug";
		}
		if (month == 9) {
			ms = "Sep";
		}
		if (month == 10) {
			ms = "Oct";
		}
		if (month == 11) {
			ms = "Nov";
		}
		if (month == 12) {
			ms = "Dec";
		}
		//return month name, day, and year of a date object as a readable string formatted to the correct output for this program
		return ms + " " + day + ", " + year;
	}

	/**
	 * this method compares the date within each Date object by comparing the year within each Date object, then the month if the two date objects have an equal year, then the day if the two date objects have an equal month and year
	 * @param none
	 * @return date comparison
	 * @throws N/A
	 */
	@Override
	public int compareTo(Date o) {
		//if the first Date object has a lower year than the second Date object, return a negative number (-1) to indicate this (if the year is lower, then the entire date is lower)
		if (this.year < o.year) {
			return -1;
		}
		//if the first Date object has a higher year than the second Date object, return a positive number (1) to indicate this (if the year is higher, then the entire date is higher)
		else if (this.year > o.year) {
			return 1;
		}
		//else the two Date objects have an equal year, so compare month, then compare day if month is equal
		else {
			//if the first Date object has a lower month than the second Date object, return a negative number (-1) to indicate this (if the year is the same and the month is lower, then the entire date is lower)
			if (this.month < o.month) {
				return -1;
			}
			//if the first Date object has a higher month than the second Date object, return a positive number (1) to indicate this (if the year is the same and the month is higher, then the entire date is higher)
			else if (this.month > o.month) {
				return 1;
			}
			//else the two Date objects have an equal year and month, so compare day to compare the date of the object
			//if the first Date object has a lower day than the second Date object, return a negative number (-1) to indicate this (if the year is the same, the month is the same, and the day is lower, then the entire date is lower)
			else if (this.day < o.day) {
				return -1;
			}
			//if the first Date object has a higher day than the second Date object, return a positive number (1) to indicate this (if the year is the same, the month is the same, and the day is higher, then the entire date is higher)
			else if (this.day > o.day) {
				return 1;
			}
			//else the two Date objects have an equal date so return 0 to indicate this (year, month, and day of the first Date object are equal to year, month, and day of the second Date object)
			return 0;
		}
	}
}
