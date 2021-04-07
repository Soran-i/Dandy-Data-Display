package selectionModule;
import java.io.*;
import java.util.*;

/**
 * This class provides the methods to handle validation of the user selections using the Config Database Reader
 * @author steph
 *
 */
public class SelectionHandler {
	private ConfigDatabaseReader ConfigReader = new ConfigDatabaseReader();
	
	/**
	 * a method to check if the type of viewer is compatible with the analysis. 
	 * @param Analysis a string defining the type of analysis to be performed
	 * @param Viewer a string defining the type of viewer
	 * @return a boolean that is true if compatible and false if not
	 */
	public boolean CheckAnalysisViewer(String Analysis, String Viewer) {
		Map<String, String> AnalysisViewerMap = ConfigReader.ReadAnalysisViewerConfig(); 
	    
		String NotAvailViewers = AnalysisViewerMap.get(Analysis);
		if (NotAvailViewers.toLowerCase().indexOf(Viewer.toLowerCase()) != -1) {
			return false; 
		} else {
			return true; 
		}
		
	}
	
	/**
	 * a method for looking up the country names and finding their equivalent standard ID's
	 * @param CountryName a string with the name of the country
	 * @return the equivalent standard country ID
	 */
	public String CountryLookup(String CountryName) {
		Map<String, String> AnalysisViewerMap = ConfigReader.ReadCountryLookupConfig(); 
		return AnalysisViewerMap.get(CountryName); 
	}
	
	/**
	 * a method for checking if the selected country is compatible with the analysis
	 * @param Analysis a string analysis to be performed
	 * @param Country a string of the selected country ID
	 * @return a boolean that is true if compatible and false if not
	 */
	public boolean CheckAnalysisCountry(String Analysis, String Country) {
		Map<String, String> AnalysisCountryMap = ConfigReader.ReadAnalysisCountriesConfig(); 
		
		String NotAvailViewers = AnalysisCountryMap.get(Analysis);
		if (NotAvailViewers.toLowerCase().indexOf(Country.toLowerCase()) != -1) {
			return false; 
		} else {
			return true; 
		}
	
	}
	
	/**
	 * A method to check if the start and end years are valid
	 * @param StartYr a string representing the start year selection
	 * @param EndYr a string representing the end year selection
	 * @return a boolean that is true if compatible and false if not
	 */
	public boolean CheckYears(String StartYr, String EndYr) {
		if (Integer.parseInt(StartYr) <= Integer.parseInt(EndYr)) {
			return true;
		}else {
			return false;
		}
	
	}
	
	
}
