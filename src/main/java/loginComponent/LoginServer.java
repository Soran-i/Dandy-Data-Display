package loginComponent;

import java.io.FileNotFoundException;
import java.io.FileReader;


import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @uathor Soran
 * Real login server deligated by the proxy server
 */

public class LoginServer implements Login {

    private String userInfoFile;

	/**
	 * @author Soran
	 * @param userInfoFile JSON file that contains all userinfo
	 */
    public LoginServer(String userInfoFile) {
        this.userInfoFile = userInfoFile;
    }

	public boolean verifyUserInfo(JsonObject userCredentials)
			throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser parser = new JsonParser();
		JsonArray userinfo = (JsonArray) parser.parse(new FileReader(userInfoFile));
		//System.out.println(userinfo);

		for (Object userObj : userinfo) {
			JsonObject userJson = (JsonObject) userObj;
			//System.out.println(userJson.toString());
			if (userCredentials.equals(userJson)) {
				return true;
			}
		}

		return false;
	}
}
