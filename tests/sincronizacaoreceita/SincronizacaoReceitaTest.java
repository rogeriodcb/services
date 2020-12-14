package sincronizacaoreceita;

import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Test;

/**
* <b>Class SincronizacaoReceitaTest</b><br><br>
* Class to test the methods from <b>SincronizacaoReceita.java</b>
* @author      Rogério de C. Brito <krcbrito@gmail.com>
* @date 12/13/2020
*/
public class SincronizacaoReceitaTest { 	

	/**
	* <b>setup</b><br><br>
	* Initialize global variables to be used in the all tests.
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/13/2020
	*/
	@BeforeClass
	public static void setUp() {
		System.out.println("\n************** SincronizacaoReceita Test **************");
			
	}
	
	/**
	* <b>testSyncWithService method</b><br><br>
	* Call SyncWithService and test:
	* <li> if the method atualizarConta from ReceitaService returns true when all parameters are ok;
	* <li> if the method atualizarConta from ReceitaService returns false when one parameter is wrong.
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/12/2020
	*/
	@Test
	public void testSyncWithService() {
		
		/***************** initial setup *****************/
		System.out.println("Testing SyncWithService method...");
		String[] t1 = new String[] {"0101", "123456", "100.50", "A"};
		String[] t2 = new String[] {"101", "123456", "100.50", "A"}; // agency wrong size
		String[] t3 = new String[] {"0101", "23456", "100.50", "A"}; // account wrong size
		String[] t4 = new String[] {"0101", "123456", "100.50", "C"}; // wrong status
		// the third parameter was not tested due RecietaService is not handle this type of error.
		
		/***************** Execute and verify*****************/
		assertEquals(SincronizacaoReceita.SyncWithService(t1),true);
		assertEquals(SincronizacaoReceita.SyncWithService(t2),false);
		assertEquals(SincronizacaoReceita.SyncWithService(t3),false);
		assertEquals(SincronizacaoReceita.SyncWithService(t4),false);
		
		System.out.println("done.");
	}
	
	/**
	* <b>testCSVReadAndSync method</b><br><br>
	* This test case shall be test:
	* <li> the behavior of given input file name;
	* <li> the behavior of given output file name;
	* <li> the behavior of given correct input file;
	* <li> the behavior of given wrong input file;
	* 
	* @author      Rogério de C. Brito <krcbrito@gmail.com>
	* @date 12/12/2020
	*/
	@Test
	public void testCSVReadAndSync() {
		
		/***************** initial setup *****************/
		System.out.println("Testing CSVReadAndSync method...");
		String correctInputFile="C:\\temp\\inputExcel.csv";
		String correctOutputFile = correctInputFile.replace(".csv","_out.csv");
		String wrongInputFile="C:\\temp\\inputExcel2.csv";
		String wrongOutputFile = wrongInputFile.replace(".csv","_out.csv");
		boolean result1, result2;
		
		/***************** Execution *****************/
		result1=SincronizacaoReceita.CSVReadAndSync(correctInputFile);
		result2=SincronizacaoReceita.CSVReadAndSync(wrongInputFile);
		
		/***************** verification *****************/	
		// verify if the correct input file was not deleted
		assertTrue(Files.exists(Paths.get(correctInputFile)));
		
		// verify if the correct output file exists
		assertTrue(Files.exists(Paths.get(correctOutputFile)));

		// verify if the CSVReadAndSync method returned true (all ok)
		assertTrue(result1);
		
		// verify if the wrong input file was not deleted
		assertTrue(Files.exists(Paths.get(wrongInputFile)));
				
		// verify if the wrong output file exists
		assertTrue(Files.exists(Paths.get(wrongOutputFile)));

		// verify if the CSVReadAndSync method returned false (the first agency is wrong)
		assertFalse(result2);
		
		System.out.println("done.");
		}

}
