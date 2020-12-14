package csv_manager;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
* <b>Class CSVManagerTest</b><br><br>
* Class to test the methods from <b>CSVManager.java</b>
* @author      Rogério de C. Brito <krcbrito@gmail.com>
* @date 12/13/2020
* 
* TODO: test runtime erros
*/
public class CSVManagerTest {

	static String validfile;
	
	/**
	* <b>setup</b><br><br>
	* Initialize global variables to be used in the all tests.
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/13/2020
	*/
	@BeforeClass
	public static void setUp() {
		System.out.println("\n************** CSVManager Test **************");
		validfile = "c:\\temp\\inputExcel.csv";
			
	}
	
	/**
	* <b>tearDown</b><br><br>
	* Executed after each test execution.
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/13/2020
	*/
	@After
	public void tearDown() {
		System.out.println("done.");
	}

	
	/**
	* <b>testGetLine method</b><br><br>
	* Test the behavior of reading input file.
	* To do this test an instance shall be created of CSVManager and then close each file (output and input).
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/13/2020
	* 
	* TODO: test if file not exist.
	*/
	@Test
	public void testGetLine() {
		
		/***************** initial setup *****************/
		System.out.println("Testing GetLine mehotd...");
		String[] s1;
		CSVManager validcsv = new CSVManager(validfile,validfile.replace(".csv", "_getlinetest.csv"));
				
		/***************** Execution *****************/
		s1 = validcsv.GetLine();
		
		/***************** verification *****************/		
		assertTrue(s1.length > 0);
		
		/***************** finalizing *****************/	
		assertTrue(validcsv.CloseInputFile());
		assertTrue(validcsv.CloseOutputFile());
			
	}
	
	/**
	* <b>testWriteLine method</b><br><br>
	* Test the behavior of writing output file.
	* To do this test an instance shall be created of CSVManager and then close each file (output and input).
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @throws IOException 
	* @date 12/13/2020
	*/
	@Test
	public void testWriteLine() throws IOException {
		
		/***************** initial setup *****************/
		System.out.println("Testing WriteLine mehotd...");
		String[] s1 = {"First column","second column"}; // 2 columns with strings
		String[] s2 = {"12.22","13,45"};				// 2 columns with numbers by using , and .
		String[] s3 = {"col 1","col 2", "col3"};  		// 3 columns
		CSVManager validcsv = new CSVManager(validfile,validfile.replace(".csv", "_writelinetest.csv"));
				
		
		/***************** Execution and verification *****************/
		// test simple writing to output file
		assertTrue(validcsv.WriteLine(s1,"OK"));
		assertTrue(validcsv.WriteLine(s2,"FAIL"));
		assertTrue(validcsv.WriteLine(s3, "last column"));
		
		/***************** finalizing *****************/	
		assertTrue(validcsv.CloseInputFile());
		assertTrue(validcsv.CloseOutputFile());

	}
		
	/**
	* <b>testContentOutputFile method</b><br><br>
	* Test if the text written by testWriteLine() method of this test case file are correct.
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @throws IOException 
	* @date 12/13/2020
	*/
	@Test
	public void testContentOutputFile() throws IOException {
		
		/***************** initial setup *****************/
		System.out.println("Testing Content of output file by testWriteLine mehotd...");
		//String validfile = "c:\\temp\\inputExcel.csv";
		
		/***************** Execution and verification *****************/
		BufferedReader br = new BufferedReader(new FileReader(validfile.replace(".csv","_writelinetest.csv")));
		try {
		    String line = br.readLine();
		    assertEquals("First column;second column;OK",line);
		    line = br.readLine();
		    assertEquals("12.22;13,45;FAIL",line);
		    line = br.readLine();
		    assertEquals("col 1;col 2;col3;last column",line);
		} finally {
		    br.close();
		}

	}
}
