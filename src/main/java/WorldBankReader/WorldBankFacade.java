package WorldBankReader;


import java.util.Iterator;
import java.util.Vector;

import ExceptionsPack.ReaderException;

/**
 * This class implements a facade design pattern since it hides the internal requirements of making data requests 
 * from the API
 * 
 * @author ginaj
 *
 */
public class WorldBankFacade {
	private WorldBankFetcher fetcher;
	private WorldBankDataParser parser;

	/**
	 * A constructor for creating a world bank data fetcher and a world bank data parser to fetch the data and parse the json
	 * and create a reader results object
	 */
	public WorldBankFacade() {
		fetcher = new WorldBankFetcher();
		parser = new WorldBankDataParser();
	}

	/**
	 * This method requests the data of start year, end year, indicator, and country from the Worldbank API
	 * 
	 * @param startYear the start year for the api request
	 * @param endYear the end year for the api request
	 * @param indicator the data indicator for the api request
	 * @param country the country symbol for the API request
	 * @throws ReaderException an exception thrown when the data cannot be requested
	 * @return ReaderResults a results struct given to the analysis
	 */
	public ReaderResults RequestData(String indicator, String startYear, String endYear, String country) throws ReaderException {
		
		fetcher.getData(startYear, endYear, indicator, country);
		ReaderResults Res = new ReaderResults();
		try {
			Res = parser.parserJson(fetcher.makeRequest());
		} catch (Exception e) {
			throw new ReaderException("Database Errors");
		}
		return Res;
	}
	
	/**
	 * This method checks if the selection is made or not (null).
	 * 
	 * @param Data vector of numeric data to be checked
	 * @return Return false if the values are null or unable to return. Return true
	 *         if the values are selected.
	 */
	public boolean checkIfAllNull(Vector<Double> Data) {
		Iterator<Double> value = Data.iterator();
		while (value.hasNext()) {
            if (value.next() != 0.0) {
            	return false;   //Return false if all values are not false
            }
        }
		
		return true;  //Returns true if all elements in the check are true. 
		
	}
}
