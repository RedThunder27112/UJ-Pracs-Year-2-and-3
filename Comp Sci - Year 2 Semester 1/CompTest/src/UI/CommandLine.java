package UI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine 
{
	
	private static void outputCMD(Process process) throws IOException //outputs cmd results
	{
		//for printing results
	    BufferedReader buffReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    
	    String line = "";
	    while (buffReader.readLine() != null)
	    {
	    	line = buffReader.readLine();
	        System.out.println(line);//prints line
	    }
	}

	
	public static void execCMD() 
	{
		//connection to command line
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("cmd /c start SyncAzureBlob.bat", null, new File("docs"));
		} catch (IOException e) 
		{
			System.err.println("Error: Running Command line");
			e.printStackTrace();
		}
		try 
		{
			outputCMD(process);
		} catch (IOException e) 
		{		
			System.err.println("Error: Printing Command line");
			e.printStackTrace();
		}
	
	}



}
