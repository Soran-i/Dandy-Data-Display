package WorldBankReader;

import java.util.Enumeration;
import java.util.Vector;

import AnalysisComponent.Analysis;
import AnalysisComponent.ParamStruct;
import AnalysisComponent.PolnForestAnalysis;
import AnalysisComponent.ResultsStruct;

/**
 * This class makes a request and requests the data
 * 
 * @author ginaj
 *
 */
public class WorldBankFacade {
	private WorldBankFetcher fetcher;
	private WorldBankDataParser parser;

	// Constructor of the class
	public WorldBankFacade() {
		fetcher = new WorldBankFetcher();
		parser = new WorldBankDataParser();
	}

	/**
	 * This method requests the data of start year, end year, indicator, and country
	 * 
	 * @param startYear
	 * @param endYear
	 * @param indicator
	 * @param country
	 */
	public void requestData(String startYear, String endYear, String indicator, String country) {
		makeRequest(startYear, endYear, indicator, country);
	}

	/**
	 * This method makes a request of the data from fetcher and parser.
	 * 
	 * @param startYear
	 * @param endYear
	 * @param indicator
	 * @param country
	 */
	private void makeRequest(String startYear, String endYear, String indicator, String country) {
		fetcher.getData(startYear, endYear, indicator, country);
		parser.parserJson(fetcher.makeRequest());
	}
	
	public static void main(String[] args)
	{
		WorldBankFacade WorldReader = new WorldBankFacade(); 
		WorldReader.requestData("2000", "2004", "EN.ATM.CO2E.PC", "CAN");
	}
	

}
