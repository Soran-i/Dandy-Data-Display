package selectionModule;
import java.util.*;

/**
 * A class for fetching the initial configurations to populate the GUI 
 * @author steph
 *
 */
public class InitialConfigFetcher {
	private ConfigDatabaseReader configReader = new ConfigDatabaseReader();
	Map<String, String> InitialConfigMap = new HashMap<String, String>(); 
	
	/**
	 * a constructor to creat the initial config fetcher and populate the Config map from the initial config database
	 */
	public InitialConfigFetcher() {
		InitialConfigMap = configReader.ReadIntialConfig(); 
	}
	
	/**
	 * a method for getting the available analyses from the config database. 
	 * @return a string of vector containing the names of the available analyses. 
	 */
	public Vector<String> getAnalysisAvailable() {
		String AnalysisString = InitialConfigMap.get("Analyses"); 
		String[] AnalysisArray = AnalysisString.split(",", -1); 
		Vector<String> Analysisvector = new Vector<String>(Arrays.asList(AnalysisArray)); 
		return Analysisvector; 
	}
	
	/**
	 * a method for getting the avaialble countries from the config database
	 * @return a vector of strings containing available countyr names
	 */
	public Vector<String> getCountriesAvailable() {
		String CountriesString = InitialConfigMap.get("Countries"); 
		String[] CountriesArray = CountriesString.split(",", -1); 
		Vector<String> CountriesVector = new Vector<String>(Arrays.asList(CountriesArray));
		return CountriesVector; 
	}
	
	/**
	 * a method for getting the available viewer
	 * @return a vector of string containing the available viewer names
	 */
	public Vector<String> getViewersAvailable() {
		String ViewersString = InitialConfigMap.get("Viewers"); 
		String[] ViewersArray = ViewersString.split(",", -1); 
		Vector<String> Viewersvector = new Vector<String>(Arrays.asList(ViewersArray));
		return Viewersvector; 
	}
	
	/**
	 * a method for getting the available years on the gui
	 * @return a vector of strings containing the available years
	 */
	public Vector<String> getYearsAvailable() { 
		String YearsString = InitialConfigMap.get("Years"); 
		String[] YearsArray = YearsString.split(",", -1); 
		
		int start = Integer.valueOf(YearsArray[0]); 
		int end = Integer.valueOf(YearsArray[1]); 
		String [] YearsFullArray = new String[end-start+1]; 

		for (int i = 0; i <= end-start ; i++) 
		{
			YearsFullArray[i] = Integer.toString(i+start); 
		} 
		Vector<String> yearsFullVector = new Vector<String>(Arrays.asList(YearsFullArray));
		return yearsFullVector; 
	}
	
}
