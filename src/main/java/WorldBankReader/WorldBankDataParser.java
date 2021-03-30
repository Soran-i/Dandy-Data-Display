package WorldBankReader;

import java.text.DecimalFormat;
import java.util.*;

import com.google.gson.JsonArray;


/**
 * This class is a parser to display the data
 * 
 * @author ginaj
 *
 */
public class WorldBankDataParser {

	// constructor
	public WorldBankDataParser() {

	}

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
		
		if(checkIfAllNull(DataVect)) {
			ThrowNoDataException(); 
		}
		
		Collections.reverse(DataVect);
		Collections.reverse(YearVect);
		
		ReaderResults ReaderRes = new ReaderResults(); 
		ReaderRes.NumericData = DataVect; 
		ReaderRes.Years = YearVect; 
		
		return ReaderRes; 
		
		
	}
	
	private boolean checkIfAllNull(Vector<Double> Data) {
		Iterator<Double> value = Data.iterator();
		while (value.hasNext()) {
            if (value.next() != 0.0) {
            	return false;   //Return false if all values are not false
            }
        }
		
		return true;  //Returns true if all elements in the check are true. 
		
	}
	
	private void ThrowNoDataException() throws Exception {
		throw new Exception("Not Valid Year Selection");
	}

		

}
