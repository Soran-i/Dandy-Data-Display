package loginComponent;

import java.io.FileNotFoundException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * @author Soran
 * 
 * Proxy server class
 */

public class LoginProxy implements Login {

    private String userInfoFile;
    private LoginServer loginServer;

    /**
     * @author Soran
     * @param userInfoFile JSON file that contains all userinfo
     */
    public LoginProxy(String userInfoFile) {
        this.userInfoFile = userInfoFile;
    }

    public boolean verifyUserInfo(JsonObject info) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        if (loginServer == null) {
            loginServer = new LoginServer(userInfoFile);
        }

        if (loginServer.verifyUserInfo(info)) {
            return true;
        }
        else {
            return false;
        }
    }

    
}
