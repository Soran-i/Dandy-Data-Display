package selectionModule;

//Java program to reading
//text file to HashMap

import java.io.*;
import java.util.*;

public class ConfigDatabaseReader {
	private String AnalysisViewersPath;
	private String AnalysisCountriesPath;
	private String InitialConfigPath;
	private String CountryLookupPath;

	
	public ConfigDatabaseReader(){
		AnalysisViewersPath = "AnalysisViewerConfig.txt";
		AnalysisCountriesPath = "AnalysisCountriesConfig.txt";
		InitialConfigPath = "InitialConfig.txt";
		CountryLookupPath = "countrylookup.txt";
        //System.out.println(AnalysisViewersPath);
        //System.out.println(AnalysisCountriesPath);
	}
	
	public Map<String, String> ReadCountryLookupConfig(){
		return HashMapFromTextFile(CountryLookupPath);
	}
	
	public Map<String, String> ReadAnalysisViewerConfig(){
		return HashMapFromTextFile(AnalysisViewersPath);
	}
	
	public Map<String, String> ReadAnalysisCountriesConfig(){
		return HashMapFromTextFile(AnalysisCountriesPath);
	}
	
	public Map<String, String> ReadIntialConfig(){
		return HashMapFromTextFile(InitialConfigPath);
	}

	private static Map<String, String> HashMapFromTextFile(String filePath)
	{
	
	    Map<String, String> map
	        = new HashMap<String, String>();
	    BufferedReader br = null;
	
	    try {
	
	        // create file object
	        File file = new File(filePath);
	
	        // create BufferedReader object from the File
	        br = new BufferedReader(new FileReader(file));
	
	        String line = null;
	
	        // read file line by line
	        while ((line = br.readLine()) != null) {
	
	            // split the line by :
	            String[] parts = line.split(":");
	
	            // first part is name, second is number
	            String name = parts[0].trim();
	            String number = parts[1].trim();
	
	            // put name, number in HashMap if they are
	            // not empty
	            if (!name.equals("") && !number.equals(""))
	                map.put(name, number);
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	
	        // Always close the BufferedReader
	        if (br != null) {
	            try {
	                br.close();
	            }
	            catch (Exception e) {
	            };
	        }
	    }

    return map;
	}
	
	
	
//	public static void main(String[] args)
//	{
//		ConfigDatabaseReader configReader = new ConfigDatabaseReader(); 
//	
//	    // read text file to HashMap
//	    Map<String, String> mapFromFile
//	        = configReader.ReadCountryLookupConfig();
//	
//	    // iterate over HashMap entries
//	    for (Map.Entry<String, String> entry :
//	         mapFromFile.entrySet()) {
//	        System.out.println(entry.getKey() + " : "
//	                           + entry.getValue());
//	    }
//	}
}
	
