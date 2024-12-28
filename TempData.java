//This class stores all of the temperature anomaly data for this program. It creates a TempData object to do this. This object stores the temperature anomaly (converted to F) and date (using a date object) for each temperature anomaly data point. This class also provides getters for each piece of the TempData object, a toString method to convert this object to a readable string, and a compareTo method to compare the temperature anomaly within each TempData object.
public class TempData implements Comparable<TempData> {
	//instance variables
	private double tempAnom;
	private Date date;
	
	//constructor
	public TempData(double tempAnom, Date date) {
		this.tempAnom = tempAnom * 1.8; //multiply temperature anomaly by 1.8 to convert input temperature anomaly to temperature anomaly in F
		this.date = date;
	}
	
	//getter for temperature anomaly
	public double getTempAnom() {
		return tempAnom;
	}
	
	//getter for date
	public Date getDate() {
		return date;
	}
	
	//this method converts each TempData object to a readable string formatted to the correct output for this program (it converts each TempData object into a string simply containing the object's temperature anomaly rounded to two decimal points with a space before it as this is what is needed in the driver)
	@Override
	public String toString() {
		return String.format(" %.2f", tempAnom);
	}

	/**
	 * this method compares temperature anomaly within each TempData object
	 * @param none
	 * @return temperature anomaly comparison
	 * @throws N/A
	 */
	@Override
	public int compareTo(TempData o) {
		//if the first TempData object has a lower temperature anomaly than the second TempData object, return a negative number (-1) to indicate this
		if (this.tempAnom < o.tempAnom) {
			return -1;
		}
		//if the first TempData object has a higher temperature anomaly than the second TempData object, return a positive number (1) to indicate this
		else if (this.tempAnom > o.tempAnom) {
			return 1;
		}
		//else the two TempData objects have an equal temperature anomaly so return 0 to indicate this
		else {
			return 0;
		}
	}
}
