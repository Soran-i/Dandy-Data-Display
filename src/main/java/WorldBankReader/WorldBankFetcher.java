package WorldBankReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * This class formats the URL string and gets the data after requesting data.
 * 
 * @author ginaj
 *
 */
public class WorldBankFetcher {
	private String urlString;

	// constructor
	public WorldBankFetcher() {
	}

	// structure - return unit of vector of data, vector of years, object called
	// readerResult
	/**
	 * This method gets the data from the URL string from the World bank API and stores it internally
	 * 
	 * @param startYear
	 * @param endYear
	 * @param indicator
	 * @param country
	 */
	public void getData(String startYear, String endYear, String indicator, String country) {
		// return Vector<Float> numericData, Vector<Integer> Years, String units
		formatURLString(startYear, endYear, indicator, country);
	}

	/**
	 * This methods takes the URL and formats the string correctly. 
	 * 
	 * @param startYear
	 * @param endYear
	 * @param indicator
	 * @param country
	 */
	private void formatURLString(String startYear, String endYear, String indicator, String country) {
		urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country,
				indicator, startYear, endYear);
	}

	/**
	 * This method requests the data from the internally formatted stored and formatted URL
	 * @return returns a JSON array containing the data from the request. 
	 */
	public JsonArray makeRequest() {
		JsonArray jsonArray = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				jsonArray = new JsonParser().parse(inline).getAsJsonArray();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
			System.out.println("Could not make a request.");
		}
		return jsonArray;

	}

}
