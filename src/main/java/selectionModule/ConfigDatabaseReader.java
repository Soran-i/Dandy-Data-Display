package selectionModule;

//Java program to reading
//text file to HashMap

import java.io.*;
import java.util.*;


/**
 * Class interfaces with the configuration database and offers back a hash map such that the calling clients can perform look ups with the config files
 * @author steph
 * 
 */
public class ConfigDatabaseReader {
	private String AnalysisViewersPath;
	private String AnalysisCountriesPath;
	private String InitialConfigPath;
	private String CountryLookupPath;

	
	/**
	 * constructor for ConfigDatabaseReader class that sets the paths to find the configuration database text files
	 */
	public ConfigDatabaseReader(){
		AnalysisViewersPath = "src/test/resources/AnalysisViewerConfig.txt";
		AnalysisCountriesPath = "src/test/resources/AnalysisCountriesConfig.txt";
		InitialConfigPath = "src/test/resources/InitialConfig.txt";
		CountryLookupPath = "src/test/resources/countrylookupConfig.txt";
	}
	
	/**
	 * reads a hashmap from the countryLookupConfig text file that maps the full country names to the standardized ID's to be used in the data requests
	 * @return a hash map of strings
	 */
	public Map<String, String> ReadCountryLookupConfig(){
		return HashMapFromTextFile(CountryLookupPath);
	}
	
	/**
	 * reads a hashmap from the AnalysisViewer config which contains all viewers NOT compatible with eac type of analysis
	 * @return a hash map of strings of that contains all viewers NOT compatible with each analysis
	 */
	public Map<String, String> ReadAnalysisViewerConfig(){
		return HashMapFromTextFile(AnalysisViewersPath);
	}
	
	/**
	 * reads the AnalysisCountiresConfig file hash map of strings to strings with analyses NOT compatible with each analysis
	 * @return a hash map of strings to strings with analyses NOT compatible with each analysis
	 */
	public Map<String, String> ReadAnalysisCountriesConfig(){
		return HashMapFromTextFile(AnalysisCountriesPath);
	}
	
	/**
	 * Reads an InitialConfig hashmap mapping the initial config lists to the initial parameters to populate the lists
	 * @return a hashmap mapping the initial config lists to the initial parameters to populate the lists
	 */
	public Map<String, String> ReadIntialConfig(){
		return HashMapFromTextFile(InitialConfigPath);
	}

	/**
	 * This function reads the text files and returns a hashmap
	 * @param filePath listing the relative path to the text file
	 * @return a hashmap from the textfile that is read from filePath
	 */
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

}
	
