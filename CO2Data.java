//This class stores all of the CO2 data for this program. It creates a CO2Data object to do this. This object stores the average CO2 concentration and date (using a date object) for each CO2 data point. This class also provides getters for each piece of the CO2Data object, a toString method to convert this object to a readable string, and a compareTo method to compare the average CO2 concentration within each CO2Data object.
public class CO2Data implements Comparable<CO2Data> {
	//instance variables
	private double avgCO2;
	private Date date;
	
	//constructor
	public CO2Data(double avgCO2, Date date) {
		this.avgCO2 = avgCO2;
		this.date = date;
	}
	
	//getter for average CO2 concentration
	public double getAvgCO2() {
		return avgCO2;
	}
	
	//getter for date
	public Date getDate() {
		return date;
	}
	
	//this method converts each CO2Data object to a readable string formatted to the correct output for this program (it converts each CO2Data object into a string simply containing the object's average CO2 concentration with a space before it as this is what is needed in the driver)
	@Override
	public String toString() {
		return " " + avgCO2;
	}

	/**
	 * this method compares average CO2 concentration within each CO2Data object
	 * @param none
	 * @return average CO2 comparison
	 * @throws N/A
	 */
	@Override
	public int compareTo(CO2Data o) {
		//if the first CO2Data object has a lower average CO2 concentration than the second CO2Data object, return a negative number (-1) to indicate this
		if (this.avgCO2 < o.avgCO2) {
			return -1;
		}
		//if the first CO2Data object has a higher average CO2 concentration than the second CO2Data object, return a positive number (1) to indicate this
		else if (this.avgCO2 > o.avgCO2) {
			return 1;
		}
		//else the two CO2Data objects have an equal average CO2 concentration so return 0 to indicate this
		else {
			return 0;
		}
	}
}
