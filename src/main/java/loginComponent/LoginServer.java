package loginComponent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class LoginServer implements Login {

    private String userInfoFile;

    public LoginServer(String userInfoFile) {
        this.userInfoFile = userInfoFile;
    }

	public boolean verifyUserInfo(JsonObject userCredentials)
			throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		JsonParser parser = new JsonParser();
		JsonArray userinfo = (JsonArray) parser.parse(new FileReader(userInfoFile));
		System.out.println(userinfo);

		for (Object userObj : userinfo) {
			JsonObject userJson = (JsonObject) userObj;
			System.out.println(userJson.toString());
			if (userCredentials.equals(userJson)) {
				return true;
			}
		}

		return false;
	}

    // private Object readJsonFile() throws IOException, ParseException {
    //     FileReader fileReader = new FileReader(userInfoFile);
    //     JSONParser jsonParser = new JSONParser();
    //     return jsonParser.parse(fileReader);
    // }

//    public boolean verifyUserInfo(JsonObject userCredentials) throws JsonIOException, JsonSyntaxException, FileNotFoundException{
//        JsonParser parser = new JsonParser();
//        JsonArray userinfo = (JsonArray) parser.parse(new FileReader(userInfoFile));
//        System.out.println(userinfo);
//
//        for (Object userObj : userinfo) {
//            JsonObject userJson = (JsonObject) userObj;
//            System.out.println(userJson.toString());
//            if (userCredentials.equals(userJson)) {
//                return true;
//            }
//        }
//
//        return false;
//    }



}
