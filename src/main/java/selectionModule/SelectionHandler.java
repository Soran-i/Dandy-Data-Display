package selectionModule;
import java.io.*;
import java.util.*;

public class SelectionHandler {
	private ConfigDatabaseReader ConfigReader = new ConfigDatabaseReader();
	
	public boolean CheckAnalysisViewer(String Analysis, String Viewer) {
		Map<String, String> AnalysisViewerMap = ConfigReader.ReadAnalysisViewerConfig(); 
	    
		String NotAvailViewers = AnalysisViewerMap.get(Analysis);
		if (NotAvailViewers.toLowerCase().indexOf(Viewer.toLowerCase()) != -1) {
			return false; 
		} else {
			return true; 
		}
		
	}
	
	public boolean CheckAnalysisCountry(String Analysis, String Country) {
		Map<String, String> AnalysisCountryMap = ConfigReader.ReadAnalysisCountriesConfig(); 
		
		String NotAvailViewers = AnalysisCountryMap.get(Analysis);
		if (NotAvailViewers.toLowerCase().indexOf(Country.toLowerCase()) != -1) {
			return false; 
		} else {
			return true; 
		}
	
	}
	
	public boolean CheckYears(String StartYr, String EndYr) {
		
		if (Integer.parseInt(StartYr) >= Integer.parseInt(EndYr)) {
			return true;
		}else {
			return false;
		}
	
	}
	
	public static void main(String[] args)
	{
		SelectionHandler SelectionHandle = new SelectionHandler(); 
		
		String Analysis = "Emissions vs Energy vs Pollution"; 
		String Viewer = "line"; 
		String Country = "us"; 
	
		boolean ViewerCheck = SelectionHandle.CheckAnalysisViewer(Analysis, Viewer);
		System.out.println(ViewerCheck)	;	
		boolean countryCheck = SelectionHandle.CheckAnalysisCountry(Analysis, Country); 
		System.out.println(countryCheck)	;
		
	}
	
}
