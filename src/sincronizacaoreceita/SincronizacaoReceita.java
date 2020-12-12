package sincronizacaoreceita;

import csv_manager.CSVManager;

/**
* <b>SincronizacaoReceita</b><br><br>
* Based in the original code SincronizacaoReceita.java from Sicredi.
* <p>Modified by</p>
* @author      Rogério de C. Brito <krcbrito@gmail.com>
* 
*/
public class SincronizacaoReceita {
	
	
	
	/**
	* Read CSV file and call "SyncWithService" method. 
	* 
	* @param file (String) - CSV file path;
	* @return execution is successfully (true) otherwise (false).
	*/
	public static boolean CSVReadAndSync(String file) {
		String[] s;
		boolean successStatus = true;
		System.out.println("Processing input file: " + file + "\nGenerating output file: "+file.replace(".csv", "_out.csv"));
		CSVManager csv = new CSVManager(file,file.replace(".csv", "_out.csv"));
		// read and write the field names
		if (!csv.WriteLine(csv.GetLine(), "Result")) {
			successStatus = false;

		}
		

		// try to read each line and call sync method
		while((s = csv.GetLine()) != null) {
			
			if(SyncWithService(s)){
				if (!csv.WriteLine(s,"Ok"))
					successStatus = false;
			}
			else {
				csv.WriteLine(s, "FAIL");
				successStatus = false;
			}
		}	
		

		
		// close files
		if(!csv.CloseInputFile()) {
			successStatus = false;
		}
		if(!csv.CloseOutputFile()) {
			successStatus = false;
		}
		

		return successStatus;
	}
	
	/**
	* Synchronize CSV data with Service by calling "atualizarConta" method from "ReceitaService" 
	* 
	*
	* @param data (Array of String) - in this implementation the data contains:
	* <ul>
	* <li> <b>data[0]</b> (String) - agency name;
	* <li> <b>data[1]</b> (String without "-") - account;
	* <li> <b>data[2]</b> (cast to double) - balance;
	* <li> <b>data[3]</b> (String) - status can be 'A', 'I', 'B' and 'P'.
	* </ul>
	* @return execution is successfully (true) otherwise (false).
	*/
	public static boolean SyncWithService(String[] data) {
		boolean successStatus = true;
		
		ReceitaService receitaService = new ReceitaService();
        try {
        	successStatus = receitaService.atualizarConta(data[0], data[1].replace("-",""),Double.parseDouble(data[2].replace(",", ".")),data[3]);
		} catch (RuntimeException e) {
			e.printStackTrace();
			successStatus = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			successStatus = false;
		} 
		
		return successStatus;
		
	}
	
	/**
	* Main method
	* 
	* @param args (String[]) - receives the arguments from command line.<br>
	* <ul><li>the first argument - the name of CSV file</ul>
	*/
	public static void main(String[] args) {
		boolean updated = false;
		
		if (args.length < 1) {
			System.out.println("The number of arguments is lower than 1.");
			System.out.println("You must pass the CSV file path");
			System.exit(0);
		}
			
		System.out.println("Updating service...");
			
	    try {
	        if (updated=CSVReadAndSync(args[0])) {
	        	System.out.println("Update successfully.");
	        }
	        else {
	        	System.out.println("An error occurred while reading and synchronizing CSV file with ReceitaService");
	        }
	        		
		} catch (RuntimeException e) {
			System.out.println("Runtime error:");
			e.printStackTrace();
		}    
	        
	    System.out.printf("The service returned %s.",(updated==true)? "ok" : "error");
	}
}
