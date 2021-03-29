package httpTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class GetData {

	public static void main(String[] args) {

		String urlString = String.format(
				"http://api.worldbank.org/v2/country/%s/indicator/SP.POP.TOTL?date=2000:2001&format=json", "can");
		System.out.println(urlString);
		int populationForYear = 0;
		int cummulativePopulation = 0;
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
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				int year;
				for (int i = 0; i < sizeOfResults; i++) {
					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						populationForYear = 0;
					else
						populationForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsInt();

					System.out.println("Population for : " + year + " is " + populationForYear);
					cummulativePopulation = cummulativePopulation + populationForYear;
				}
				System.out.println(
						"The average population over the selected years is " + cummulativePopulation / sizeOfResults);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}

		return;
	}

}


