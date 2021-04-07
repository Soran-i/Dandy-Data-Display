package WorldBankReader;

import java.text.DecimalFormat;
import java.util.*;

import com.google.gson.JsonArray;

import ExceptionsPack.ReaderException;


/**
 * This class is a parser to fetch parse the returned JSON file from the WorldBank api
 * @author ginaj
 *
 */
public class WorldBankDataParser {

	/**
	 * constructor for the parser
	 */
	public WorldBankDataParser() {

	}

	/**
	 * Method used by the World Bank Facade to parse the Json return
	 * @param jsonArray a vector of percentages
	 * @return a reader results structure carrying the data from the data fetching
	 */
	public ReaderResults parserJson(JsonArray jsonArray) throws Exception {
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
		Vector<Double> DataVect = new Vector<Double>(); 
		Vector<Integer> YearVect = new Vector<Integer>(); 
		
		DecimalFormat df = new DecimalFormat("#.##");      
		
		for (int i = 0; i < sizeOfResults; i++) {
			int year; 
			double data; 
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				data = 0.0;
			else
				data = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
			
			data = Double.valueOf(df.format(data));
			DataVect.addElement(data);
			
			YearVect.addElement(year); 

		}
		
		Collections.reverse(DataVect);
		Collections.reverse(YearVect);
		
		ReaderResults ReaderRes = new ReaderResults(); 
		ReaderRes.NumericData = DataVect; 
		ReaderRes.Years = YearVect; 
		
		return ReaderRes; 
		
	}
		

}
