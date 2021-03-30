package WorldBankReader;

import java.util.Enumeration;
import java.util.Vector;

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
	 * @throws Exception 
	 */
	public ReaderResults RequestData(String indicator, String startYear, String endYear, String country) throws Exception {
		
		fetcher.getData(startYear, endYear, indicator, country);
		ReaderResults Res = parser.parserJson(fetcher.makeRequest());
		return Res;
	}

	/**
	 * This method makes a request of the data from fetcher and parser.
	 * 
	 * @param startYear
	 * @param endYear
	 * @param indicator
	 * @param country
	 * @throws Exception 
	 */
	
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
