package csv_manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVManager {
	
	BufferedReader csvReader = null;
	FileWriter csvWriter =null;
	boolean inOK = false;
	boolean outOK = false;
	
	/**
	* Constructor that opens input and output CSV files. 
	* 
	* @param inputFile (String) - CSV input file path;
	* @param outputFile (String) - CSV output file path;
	*/
	public CSVManager(String inputFile, String outputFile){
		
		// try to open the CSV file
		try {
			csvReader = new BufferedReader(new FileReader(inputFile));
			inOK = true;
		} catch (FileNotFoundException e1) {
			System.out.println("The file " +inputFile+ " was not found.");
			e1.printStackTrace();
		}
		
		// try to open the output CSV file
		try {
			csvWriter = new FileWriter(outputFile);
			outOK = true;
		} catch (IOException e) {
			System.out.println("The file " +outputFile+ " was failed to create.");
			e.printStackTrace();
		}
		
	}
	
	/**
	* Get line from CSV file and return split information into String array. 
	* 
	* @return Array of string with split line information.
	*/	
	public String[] GetLine() {
		String row;
		String[] data = null;
		
		// verify if the input file is opened
		if(inOK){
			// try to read new line
			try {
				if ((row = csvReader.readLine()) != null) {
					    data = row.split(";");
						System.out.println("Read line: "+data[0]+ " "+data[1]+" " + data[2] + " " +data[3]);

					}
			} catch (IOException e) {
				System.out.println("IO exception occurred while reading CSV file.");
				e.printStackTrace();
			}
		}
		
		return data;
	}

	/**
	* Writes a line to CSV output file and return if successfully executed.  
	* 
	* @param s (Array of String) - all strings to be write in the output file;
	* @param status (String) - writes status *ok" or "FAIL" in the last column position.
	* @return true if execution occurred successfully. Otherwise, false.
	*/	
	public boolean WriteLine(String[] s, String result) {
		
		// verify if the output file is opened
		if(outOK) {
			// try to write information to output file
			try {
				// write array
				csvWriter.append(s[0]);
				for(int i=1;i<s.length;i++) { 
					csvWriter.append(";"+s[i]);
				}
				csvWriter.append(";"+result); // write new column
				csvWriter.append("\r\n");
				return true;
				} catch (IOException e) {
					System.out.println("An error occurred while writing data to the outputfile. ");
					e.printStackTrace();
					return false;
				}
		
			}
		else
			return false;
				
	}
	
	/**
	* Close CSV input file.  
	* @return true if execution occurred successfully. Otherwise, false.
	*/	
	public boolean CloseInputFile() {
		try {
			csvReader.close();
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred while closing the input file.");
			e.printStackTrace();
			return false;
		}

	}
	

	/**
	* Close CSV output file.  
	* @return true if execution occurred successfully. Otherwise, false.
	*/
	public boolean CloseOutputFile() {
		try {
			csvWriter.flush();
			csvWriter.close();
			return true;
		} catch (IOException e) {
			System.out.println("An error occurred while closing the output file.");
			e.printStackTrace();
			return false;
		}
	}
	
}
