package WorldBankReader;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import statsVisualiser.gui.ReaderException;

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
	 * @throws Exception an exception thrown when the data cannot be requested
	 */
	public ReaderResults RequestData(String indicator, String startYear, String endYear, String country) throws ReaderException {
		
		fetcher.getData(startYear, endYear, indicator, country);
		ReaderResults Res = new ReaderResults();
		try {
			Res = parser.parserJson(fetcher.makeRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ReaderException("Database Errors");
		}
		return Res;
	}
	
	public boolean checkIfAllNull(Vector<Double> Data) {
		Iterator<Double> value = Data.iterator();
		while (value.hasNext()) {
            if (value.next() != 0.0) {
            	return false;   //Return false if all values are not false
            }
        }
		
		return true;  //Returns true if all elements in the check are true. 
		
	}

	
//	public static void main(String[] args)
//	{
//		WorldBankFacade WorldReader = new WorldBankFacade(); 
//		ReaderResults Res = new ReaderResults(); 
//		
//		try {
//			Res = WorldReader.RequestData("2000", "2005", "EN.ATM.PM25.MC.M3", "CAN");
//		} catch (Exception e) {
//			e.printStackTrace();
//			
//		}
//		
//		
//		Vector<Double> Data = Res.NumericData; 
//		
//		Enumeration vEnum = Data.elements();
//		System.out.println("\nPulled Data:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");
//		
//		
//		Vector<Integer> Years = Res.Years; 
//		
//		vEnum = Years.elements();
//		System.out.println("\nYears:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");	
//		
//		Vector<Integer> Test = new Vector<Integer>(); 
//		Test.add(2000); 
//		Test.add(2001); 
//		Test.add(2002); 
//		Test.add(2003); 
//		Test.add(2004); 
//		
//		vEnum = Test.elements();
//		System.out.println("\nTest:");
//		while(vEnum.hasMoreElements())
//		    System.out.print(vEnum.nextElement() + " ");
//	}
//	
}
