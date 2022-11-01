package co.edu.arep.service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import co.edu.arep.model.User;
import spark.Request;
import spark.Response;
import static spark.Spark.halt;
public class AuthService {

    private static Map<String, String> users = new HashMap<>(); // caché

    /**
     * Mwthod that allows the access of an user
     * @param req - request with the credentials
     * @param res - response HTTP
     * @return Message success or failure
     */
    public Object login(Request req, Response res) {
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        String response;

        boolean isValid = validate(username, password);

        if (isValid) {
            res.status(200);
            response = "Succesful Login! Welcome " + username + " to your Secure Website";
        } else {
            res.status(403);
            halt(403, "<h1>403 Forbidden, check your credentials!</h1>");
            response = "Forbidden, check your credentials!";
        }

        return response;
    }

    /**
     * Method that validate the user that wants connect to the service
     * @param username - part of the credential user
     * @param password - part of the credential user
     * @return if is valid
     */
    public boolean validate(String username, String password) {
        System.out.println("Login received");
        defaultCreate();

        boolean result = false;

        Iterator<String> it = users.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            User tmpUser = new User(username, password);
            if (tmpUser.getUsername().equals(key) && tmpUser.getPassword().equals(users.get(key))) {
                result = true;
                break;
            }
        }
        return result;

    }

    /**
     * Method that creates a test User
     */
    public void defaultCreate() {
        String username = "tester";
        String password = "1234";

        User user = new User(username, password);
        users.put(user.getUsername(), user.getPassword());
    }
}
