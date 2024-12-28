//This class stores all of the sea level data for this program. It creates a SeaData object to do this. This object stores the average sea level rise and date (using a date object) for each sea level data point. This class also provides getters for each piece of the SeaData object, a toString method to convert this object to a readable string, and a compareTo method to compare the average sea level rise within each SeaData object.
public class SeaData implements Comparable<SeaData> {
	//instance variables
	private double seaLevel; //average sea level rise from input file
	private Date date;
	
	//constructor
	public SeaData(double seaLevel, Date date) {
		this.seaLevel = seaLevel;
		this.date = date;
	}
	
	//getter for average sea level rise
	public double getSeaLevel() {
		return seaLevel;
	}
	
	//getter for date
	public Date getDate() {
		return date;
	}
	
	//this method converts each SeaData object to a readable string formatted to the correct output for this program (it converts each SeaData object into a string simply containing the object's average sea level rise with a space before it as this is what is needed in the driver)
	@Override
	public String toString() {
		return " " + seaLevel;
	}

	/**
	 * this method compares average sea level rise within each SeaData object
	 * @param none
	 * @return average sea level rise comparison
	 * @throws N/A
	 */
	@Override
	public int compareTo(SeaData o) {
		//if the first SeaData object has a lower average sea level rise than the second SeaData object, return a negative number (-1) to indicate this
		if (this.seaLevel < o.seaLevel) {
			return -1;
		}
		//if the first SeaData object has a higher average sea level rise than the second SeaData object, return a positive number (1) to indicate this
		else if (this.seaLevel > o.seaLevel) {
			return 1;
		}
		//else the two SeaData objects have an equal average sea level rise so return 0 to indicate this
		else {
			return 0;
		}
	}
}
