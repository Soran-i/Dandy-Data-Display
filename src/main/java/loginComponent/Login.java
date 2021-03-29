package loginComponent;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public interface Login {

    //public boolean verifyUserInfo(JsonObject userCredentials);
    
    public boolean verifyUserInfo(JsonObject userCredentials) throws JsonIOException, JsonSyntaxException, FileNotFoundException;
}
