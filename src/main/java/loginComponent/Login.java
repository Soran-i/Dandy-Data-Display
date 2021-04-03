package loginComponent;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * @author Soran
 * Represents the interface that is used by both the proxy and real server when logging in
 */

public interface Login {

    /**
     * @author Soran
     * @param userCredentials This is the JSON object that is going to be passed with the user credentials
     * @throws JsonIOException 
     * @throws JsonSyntaxException
     * @throws FileNotFoundException
     */
    
    public boolean verifyUserInfo(JsonObject userCredentials) throws JsonIOException, JsonSyntaxException, FileNotFoundException;
}
