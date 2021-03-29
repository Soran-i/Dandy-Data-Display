package loginComponent;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Login login = new LoginProxy("/Users/soranismail/Developer/CS 2212/Login/login/src/main/resources/userinfo.json");
    	Login login = new LoginProxy("F:\\CS2212\\ProjectProgramming\\DandyDataDisplayGithub\\Dandy-Data-Display\\src\\main\\resources\\userinfo.json");
        JsonObject user = new JsonObject();
        user.addProperty("username", "soran");
        user.addProperty("password", "74567");
        String text = user.toString();
        // System.out.println(text);
        try {
            System.out.print(login.verifyUserInfo(user));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
