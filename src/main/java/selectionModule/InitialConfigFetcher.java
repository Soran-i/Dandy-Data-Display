package selectionModule;
import java.util.*;

public class InitialConfigFetcher {
	private ConfigDatabaseReader configReader = new ConfigDatabaseReader();
	Map<String, String> InitialConfigMap = new HashMap<String, String>(); 
	
	public InitialConfigFetcher() {
		InitialConfigMap = configReader.ReadIntialConfig(); 
	}
	
	public Vector<String> getAnalysisAvailable() {
		String AnalysisString = InitialConfigMap.get("Analyses"); 
		String[] AnalysisArray = AnalysisString.split(",", -1); 
		Vector<String> Analysisvector = new Vector<String>(Arrays.asList(AnalysisArray)); 
		return Analysisvector; 
	}
	
	public Vector<String> getCountriesAvailable() {
		String CountriesString = InitialConfigMap.get("Countries"); 
		String[] CountriesArray = CountriesString.split(",", -1); 
		Vector<String> CountriesVector = new Vector<String>(Arrays.asList(CountriesArray));
		return CountriesVector; 
	}
	
	public Vector<String> getViewersAvailable() {
		String ViewersString = InitialConfigMap.get("Viewers"); 
		String[] ViewersArray = ViewersString.split(",", -1); 
		Vector<String> Viewersvector = new Vector<String>(Arrays.asList(ViewersArray));
		return Viewersvector; 
	}
	
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
	
//	public static void main(String[] args)
//	{
//		InitialConfigFetcher ConfigHandler = new InitialConfigFetcher(); 
//		String[] years= ConfigHandler.getYearsAvailable();  
//		System.out.println(Arrays.toString(years)); 
//		
//		String[] Analyses= ConfigHandler.getAnalysisAvailable();  
//		System.out.println(Arrays.toString(Analyses));
//		
//		String[] Viewers= ConfigHandler.getViewersAvailable();  
//		System.out.println(Arrays.toString(Viewers));
//		
//		String[] Countries= ConfigHandler.getCountriesAvailable();  
//		System.out.println(Arrays.toString(Countries));
//		
//	}
	
}
