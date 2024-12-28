
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * This class contains the main method for this program. It reads in data from multiple input files using multiple buffered readers, uses methods from other classes to perform the required tasks for this program, and prints the transformed data to an output file using a buffered writer.
 * @author Michael Downs
 */
public class Program2Driver {
	/**
	 * This is the main method for this program. It reads in data from multiple input files using multiple buffered readers, uses methods from the other 5 classes to perform the required operations on this data, and prints the transformed data to an output file using a buffered writer.
	 * @param args unused
	 * @throws IOException
	 * @return void
	 */
	public static void main (String[] args) throws IOException {
		//sets the input file names
  		String inputFile1 = "temperature_anomaly.csv";
  		String inputFile2 = "sea_level.csv";
  		String inputFile3 = "co2.csv";
		//creates 3 buffered readers to read data from the 3 input files
  		FileInputStream fis = new FileInputStream(inputFile1);
  		InputStreamReader isr = new InputStreamReader(fis);
  		BufferedReader stdin = new BufferedReader(isr);
  		
  		FileInputStream fis2 = new FileInputStream(inputFile2);
  		InputStreamReader isr2 = new InputStreamReader(fis2);
  		BufferedReader stdin2 = new BufferedReader(isr2);
  		
  		FileInputStream fis3 = new FileInputStream(inputFile3);
  		InputStreamReader isr3 = new InputStreamReader(fis3);
  		BufferedReader stdin3 = new BufferedReader(isr3);
  		//creates a buffered writer to write the transformed data to an output file
  		FileWriter fileWriter = new FileWriter("program2output.txt");
  	    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter, 54000);
  		
  	    try {
  	    	//initializes one left leaning red black tree with TempData as the key and Date as the value and another with Date as the key and TempData as the value
  	    	LeftRedBlackTree2<TempData, Date> temp = new LeftRedBlackTree2<>();
  	    	LeftRedBlackTree2<Date, TempData> tempDate = new LeftRedBlackTree2<>();
			//instance variable for one line of data
			String line;
			//instance variable; flag to skip the first line
			boolean firstLine = true;
			//reads from the file while there is still data to read
			while ((line = stdin.readLine()) != null) {
				//skips the first line as it is includes headers not data
				if (firstLine) {
			        firstLine = false;
			        continue; 
			    }
			    //creates an array of strings split at each column of the input file to store each part of each line of data
			    String[] rawData = line.split(",");
			    //if the line is a complete line (not a short or irregularly split line that may cause errors) (per number of splits a complete line should have based on the input file)
			    if (rawData.length >= 4) {
			    	//only use world data; ignore all lines that are either northern hemisphere data or southern hemisphere data
			    	if (!rawData[0].equals("Northern Hemisphere") && !rawData[0].equals("Southern Hemisphere")) {
			    		//instance variables for day, month, and year for Date object
				    	int day = 0;
				    	int month = 0;
				    	int year = 0;
				        //parse each of the strings that contain relevant data from the array into a variable of the correct type (i.e. String, double) so that the temperature anomaly (tempAnom) can be stored in a TempData object and the date (fullDate) can be parsed again separately
				    	String fullDate = rawData[2];
				    	double tempAnom = Double.parseDouble(rawData[3]);
				    	//creates an array of strings split at each "-" to attempt to store each part of each date
				    	String[] dparts = fullDate.split("-");
				    	//if the date was split by "-", the created array has length 3 with day, month, and year in year, month, day order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	if (dparts.length >= 3) {
				    		year = Integer.parseInt(dparts[0]);
				    		month = Integer.parseInt(dparts[1]);
				    		day = Integer.parseInt(dparts[2]);
				    	}
				    	//else the date is still one string (there were no "-"s so it wasn't split), so it must be split by "/" (per the input file), so create an array of strings split at each "/" to store each part of each date, this array again has length 3 but with day, month, and year in month, day, year order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	else {
				    		String[] dparts2 = fullDate.split("/");
				    		month = Integer.parseInt(dparts2[0]);
				    		day = Integer.parseInt(dparts2[1]);
				    		year = Integer.parseInt(dparts2[2]);
				    	}
				    	//creates a new date object that includes the day, month, and year that were parsed from the input file as integers
				    	Date date = new Date(day, month, year);
				    	//creates a new TempData object that includes the tempAnom that was parsed from the input file as a double and the recently created (above) Date object
				    	TempData tempdata = new TempData(tempAnom, date);
				    	//put the tempdata/date key/value pair in the temp tree (the left leaning red black tree with TempData as the key and Date as the value)
				    	temp.put(tempdata, date);
				    	//put the date/tempdata key/value pair in the tempDate tree (the left leaning red black tree with Date as the key and TempData as the value)
				    	tempDate.put(date, tempdata);
			    	}
			    }
			}
			//initializes one left leaning red black tree with SeaData as the key and Date as the value and another with Date as the key and SeaData as the value
  	    	LeftRedBlackTree2<SeaData, Date> sea = new LeftRedBlackTree2<>();
  	    	LeftRedBlackTree2<Date, SeaData> seaDate = new LeftRedBlackTree2<>();
			//instance variable for one line of data
			String line2;
			//instance variable; flag to skip the first line
			boolean firstLine2 = true; 
			//reads from the file while there is still data to read
			while ((line2 = stdin2.readLine()) != null) {
				//skips the first line as it is includes headers not data
				if (firstLine2) {
			        firstLine2 = false;
			        continue; 
			    }
			    //creates an array of strings split at each column of the input file to store each part of each line of data
			    String[] rawData = line2.split(",");
			    //if the line is a complete line (not a short or irregularly split line that may cause errors) (per number of splits a complete line should have based on the input file)
			    if (rawData.length >= 5) {
			    	//only use world data; ignore all lines that are either northern hemisphere data or southern hemisphere data
			    	if (!rawData[0].equals("Northern Hemisphere") && !rawData[0].equals("Southern Hemisphere")) {
			    		//instance variables for day, month, and year for Date object
				    	int day = 0;
				    	int month = 0;
				    	int year = 0;
				    	//parse each of the strings that contain relevant data from the array into a variable of the correct type (i.e. String, double) so that the average sea level rise (seaLevel) can be stored in a SeaData object and the date (fullDate) can be parsed again separately
				    	String fullDate = rawData[2];
				    	double seaLevel = Double.parseDouble(rawData[3]);
				    	//creates an array of strings split at each "-" to attempt to store each part of each date
				    	String[] dparts = fullDate.split("-");
				    	//if the date was split by "-", the created array has length 3 with day, month, and year in year, month, day order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	if (dparts.length >= 3) {
				    		year = Integer.parseInt(dparts[0]);
				    		month = Integer.parseInt(dparts[1]);
				    		day = Integer.parseInt(dparts[2]);
				    	}
				    	//else the date is still one string (there were no "-"s so it wasn't split), so it must be split by "/" (per the input file), so create an array of strings split at each "/" to store each part of each date, this array again has length 3 but with day, month, and year in month, day, year order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	else {
				    		String[] dparts2 = fullDate.split("/");
				    		month = Integer.parseInt(dparts2[0]);
				    		day = Integer.parseInt(dparts2[1]);
				    		year = Integer.parseInt(dparts2[2]);
				    	}
				    	//creates a new date object that includes the day, month, and year that were parsed from the input file as integers
				    	Date date = new Date(day, month, year);
				    	//creates a new SeaData object that includes the seaLevel that was parsed from the input file as a double and the recently created (above) Date object
				    	SeaData seadata = new SeaData(seaLevel, date);
				    	//put the seadata/date key/value pair in the sea tree (the left leaning red black tree with SeaData as the key and Date as the value)
				    	sea.put(seadata, date);
				    	//put the date/seadata key/value pair in the seaDate tree (the left leaning red black tree with Date as the key and SeaData as the value)
				    	seaDate.put(date, seadata);
			    	}
			    }
			}
			//initializes one left leaning red black tree with CO2Data as the key and Date as the value and another with Date as the key and CO2Data as the value
  	    	LeftRedBlackTree2<CO2Data, Date> co2 = new LeftRedBlackTree2<>();
  	    	LeftRedBlackTree2<Date, CO2Data> co2Date = new LeftRedBlackTree2<>();
			//instance variable for one line of data
			String line3;
			//instance variable; flag to skip the first line
			boolean firstLine3 = true; 
			//reads from the file while there is still data to read
			while ((line3 = stdin3.readLine()) != null) {
				//skips the first line as it is includes headers not data
				if (firstLine3) {
			        firstLine3 = false;
			        continue; 
			    }
			    //creates an array of strings split at each column of the input file to store each part of each line of data
			    String[] rawData = line3.split(",");
			    //if the line is a complete line (not a short or irregularly split line that may cause errors) (per number of splits a complete line should have based on the input file)
			    if (rawData.length >= 5) {
			    	//only use world data; ignore all lines that are either northern hemisphere data or southern hemisphere data
			    	if (!rawData[0].equals("Northern Hemisphere") && !rawData[0].equals("Southern Hemisphere")) {
			    		//instance variables for day, month, and year for Date object
				    	int day = 0;
				    	int month = 0;
				    	int year = 0;
				    	//parse each of the strings that contain relevant data from the array into a variable of the correct type (i.e. String, double) so that the average CO2 concentration (avgCO2) can be stored in a CO2Data object and the date (fullDate) can be parsed again separately
				    	String fullDate = rawData[2];
				    	double avgCO2 = Double.parseDouble(rawData[3]);
				    	//creates an array of strings split at each "-" to attempt to store each part of each date
				    	String[] dparts = fullDate.split("-");
				    	//if the date was split by "-", the created array has length 3 with day, month, and year in year, month, day order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	if (dparts.length >= 3) {
				    		year = Integer.parseInt(dparts[0]);
				    		month = Integer.parseInt(dparts[1]);
				    		day = Integer.parseInt(dparts[2]);
				    	}
				    	//else the date is still one string (there were no "-"s so it wasn't split), so it must be split by "/" (per the input file), so create an array of strings split at each "/" to store each part of each date, this array again has length 3 but with day, month, and year in month, day, year order (per the input file), so parse day, month, and year into variables of type int in the correct (per input file) order so that they can be stored in a Date object
				    	else {
				    		String[] dparts2 = fullDate.split("/");
				    		month = Integer.parseInt(dparts2[0]);
				    		day = Integer.parseInt(dparts2[1]);
				    		year = Integer.parseInt(dparts2[2]);
				    	}
				    	//creates a new date object that includes the day, month, and year that were parsed from the input file as integers
				    	Date date = new Date(day, month, year);
				    	//creates a new CO2Data object that includes the avgCO2 that was parsed from the input file as a double and the recently created (above) Date object
				    	CO2Data co2data = new CO2Data(avgCO2, date);
				    	//put the co2data/date key/value pair in the co2 tree (the left leaning red black tree with CO2Data as the key and Date as the value)
				    	co2.put(co2data, date);
				    	//put the date/co2data key/value pair in the co2Date tree (the left leaning red black tree with Date as the key and CO2Data as the value)
				    	co2Date.put(date, co2data);
			    	}
			    }
			}
			
			//initialize a variable that will store the key of the red black tree node with the lowest temperature anomaly
			TempData firstT = null;
			//iterate through the keys in the temp tree in order by using the keys method from the LeftRedBlackTree2 class
			for (TempData key : temp.keys()) {
				//set the variable that stores the key with the lowest temperature anomaly to the first key that is iterated through (as this key is the key with the lowest temperature anomaly as the keys method iterates through the keys in ascending order)
				firstT = key;
				//write this key (the lowest temperature anomaly) along with its value (the date on which this temperature anomaly occurred) to the output file in the correct format
				bufferedWriter.write(String.format("Lowest Temperature anomaly (F):%s on %s\n", key.toString(), temp.get(key)));
				//break the loop as the first key was the only key that needed to be iterated through
				break;
			}
			//initialize a variable that stores the date of the lowest temperature anomaly (which is obtained from the value of the key with the lowest temperature anomaly)
			Date lowtempDate = temp.get(firstT);
			//check if seaDate (the tree with Date as key and SeaData as value) contains a key that matches the date of the lowest temperature anomaly
			if (seaDate.contains(lowtempDate)) {
				//if found, write the value of this key (the average sea level rise on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Sea Level Rise was%s\n", seaDate.get(lowtempDate)));
			}
			//check if co2Date (the tree with Date as key and CO2Data as value) contains a key that matches the date of the lowest temperature anomaly
			if (co2Date.contains(lowtempDate)) {
				//if found, write the value of this key (the average CO2 concentration on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Average Co2 concentration was%s\n", co2Date.get(lowtempDate)));
			}
			
			//initialize a variable that will store the key of the red black tree node with the highest temperature anomaly
			TempData lastT = null;
			//iterate through the keys in the temp tree in order by using the keys method from the LeftRedBlackTree2 class
			for (TempData key : temp.keys()) {
				//set the variable that stores the key with the highest temperature anomaly to the current key that is iterated through (as when the loop completes, this key is the key with the highest temperature anomaly as the keys method iterates through the keys in ascending order (so the last key iterated through is the key with the highest temperature anomaly))
				lastT = key;
			}
			//write this key (the highest temperature anomaly) along with its value (the date on which this temperature anomaly occurred) to the output file in the correct format
			bufferedWriter.write(String.format("\nHighest Temperature anomaly (F):%s on %s\n", lastT.toString(), temp.get(lastT)));
			//initialize a variable that stores the date of the highest temperature anomaly (which is obtained from the value of the key with the highest temperature anomaly)
			Date hightempDate = temp.get(lastT);
			//check if seaDate (the tree with Date as key and SeaData as value) contains a key that matches the date of the highest temperature anomaly
			if (seaDate.contains(hightempDate)) {
				//if found, write the value of this key (the average sea level rise on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Sea Level Rise was%s\n", seaDate.get(hightempDate)));
			}
			//check if co2Date (the tree with Date as key and CO2Data as value) contains a key that matches the date of the highest temperature anomaly
			if (co2Date.contains(hightempDate)) {
				//if found, write the value of this key (the average CO2 concentration on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Average Co2 concentration was%s\n", co2Date.get(hightempDate)));
			}
			
			//initialize a variable that will store the key of the red black tree node with the lowest average sea level rise
			SeaData firstS = null;
			//iterate through the keys in the sea tree in order by using the keys method from the LeftRedBlackTree2 class
			for (SeaData key : sea.keys()) {
				//set the variable that stores the key with the lowest average sea level rise to the first key that is iterated through (as this key is the key with the lowest average sea level rise as the keys method iterates through the keys in ascending order)
				firstS = key;
				//write this key (the lowest average sea level rise) along with its value (the date on which this average sea level rise occurred) to the output file in the correct format
				bufferedWriter.write(String.format("\nLowest Sea Level Rise:%s on %s\n", key.toString(), sea.get(key)));
				//break the loop as the first key was the only key that needed to be iterated through
				break;
			}
			//initialize a variable that stores the date of the lowest average sea level rise (which is obtained from the value of the key with the lowest average sea level rise)
			Date lowseaDate = sea.get(firstS);
			//check if tempDate (the tree with Date as key and TempData as value) contains a key that matches the date of the lowest average sea level rise 
			if (tempDate.contains(lowseaDate)) {
				//if found, write the value of this key (the temperature anomaly on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Temperature Anomaly (F) was%s\n", tempDate.get(lowseaDate)));
			}
			//check if co2Date (the tree with Date as key and CO2Data as value) contains a key that matches the date of the lowest average sea level rise 
			if (co2Date.contains(lowseaDate)) {
				//if found, write the value of this key (the average CO2 concentration on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Average Co2 concentration was%s\n", co2Date.get(lowseaDate)));
			}
			
			//initialize a variable that will store the key of the red black tree node with the highest average sea level rise
			SeaData lastS = null;
			//iterate through the keys in the sea tree in order by using the keys method from the LeftRedBlackTree2 class
			for (SeaData key : sea.keys()) {
				//set the variable that stores the key with the highest average sea level rise to the current key that is iterated through (as when the loop completes, this key is the key with the highest average sea level rise as the keys method iterates through the keys in ascending order (so the last key iterated through is the key with the highest average sea level rise))
				lastS = key;
			}
			//write this key (the highest average sea level rise) along with its value (the date on which this average sea level rise occurred) to the output file in the correct format
			bufferedWriter.write(String.format("\nHighest Sea Level Rise:%s on %s\n", lastS.toString(), sea.get(lastS)));
			//initialize a variable that stores the date of the highest average sea level rise (which is obtained from the value of the key with the highest average sea level rise)
			Date highseaDate = sea.get(lastS);
			//check if tempDate (the tree with Date as key and TempData as value) contains a key that matches the date of the highest average sea level rise
			if (tempDate.contains(highseaDate)) {
				//if found, write the value of this key (the temperature anomaly on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Temperature Anomaly (F) was%s\n", tempDate.get(highseaDate)));
			}
			//check if co2Date (the tree with Date as key and CO2Data as value) contains a key that matches the date of the highest average sea level rise
			if (co2Date.contains(highseaDate)) {
				//if found, write the value of this key (the average CO2 concentration on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Average Co2 concentration was%s\n", co2Date.get(highseaDate)));
			}
			
			//initialize a variable that will store the key of the red black tree node with the lowest average CO2 concentration
			CO2Data firstC = null;
			//iterate through the keys in the co2 tree in order by using the keys method from the LeftRedBlackTree2 class
			for (CO2Data key : co2.keys()) {
				//set the variable that stores the key with the lowest average CO2 concentration to the first key that is iterated through (as this key is the key with the lowest average CO2 concentration as the keys method iterates through the keys in ascending order)
				firstC = key;
				//write this key (the lowest average CO2 concentration) along with its value (the date on which this average CO2 concentration occurred) to the output file in the correct format
				bufferedWriter.write(String.format("\nLowest Average Co2 concentration:%s on %s\n", key.toString(), co2.get(key)));
				//break the loop as the first key was the only key that needed to be iterated through
				break;
			}
			//initialize a variable that stores the date of the lowest average CO2 concentration (which is obtained from the value of the key with the lowest average CO2 concentration)
			Date lowco2Date = co2.get(firstC);
			//check if tempDate (the tree with Date as key and TempData as value) contains a key that matches the date of the lowest average CO2 concentration
			if (tempDate.contains(lowco2Date)) {
				//if found, write the value of this key (the temperature anomaly on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Temperature Anomaly (F) was%s\n", tempDate.get(lowco2Date)));
			}
			//check if seaDate (the tree with Date as key and SeaData as value) contains a key that matches the date of the lowest average CO2 concentration
			if (seaDate.contains(lowco2Date)) {
				//if found, write the value of this key (the average sea level rise on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Sea Level Rise was%s\n", seaDate.get(lowco2Date)));
			}
			
			//initialize a variable that will store the key of the red black tree node with the highest average CO2 concentration
			CO2Data lastC = null;
			//iterate through the keys in the co2 tree in order by using the keys method from the LeftRedBlackTree2 class
			for (CO2Data key : co2.keys()) {
				//set the variable that stores the key with the highest average CO2 concentration to the current key that is iterated through (as when the loop completes, this key is the key with the highest average CO2 concentration as the keys method iterates through the keys in ascending order (so the last key iterated through is the key with the highest average CO2 concentration))
				lastC = key;
			}
			//write this key (the highest average CO2 concentration) along with its value (the date on which this average CO2 concentration occurred) to the output file in the correct format
			bufferedWriter.write(String.format("\nHighest Average Co2 concentration:%s on %s\n", lastC.toString(), co2.get(lastC)));
			//initialize a variable that stores the date of the highest average CO2 concentration (which is obtained from the value of the key with the highest average CO2 concentration)
			Date highco2Date = co2.get(lastC);
			//check if tempDate (the tree with Date as key and TempData as value) contains a key that matches the date of the highest average CO2 concentration
			if (tempDate.contains(highco2Date)) {
				//if found, write the value of this key (the temperature anomaly on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Temperature Anomaly (F) was%s\n", tempDate.get(highco2Date)));
			}
			//check if seaDate (the tree with Date as key and SeaData as value) contains a key that matches the date of the highest average CO2 concentration
			if (seaDate.contains(highco2Date)) {
				//if found, write the value of this key (the average sea level rise on that same date) to the output file in the correct format
				bufferedWriter.write(String.format("On that same date, the Sea Level Rise was%s\n", seaDate.get(highco2Date)));
			}
			//immediately write buffered data to the file
			bufferedWriter.flush();
		}
		//catches an input output exception
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
