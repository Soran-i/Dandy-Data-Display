package WorldBankReader;

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

	public void parserJson(JsonArray jsonArray) {
		int populationForYear = 0;
		int cummulativePopulation = 0;
		int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
		int year;
		for (int i = 0; i < sizeOfResults; i++) {
			year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
			if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
				populationForYear = 0;
			else
				populationForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsInt();

			System.out.println("Population for : " + year + " is " + populationForYear);
			cummulativePopulation = cummulativePopulation + populationForYear;
		}
		System.out
				.println("The average population over the selected years is " + cummulativePopulation / sizeOfResults);
	}

}
