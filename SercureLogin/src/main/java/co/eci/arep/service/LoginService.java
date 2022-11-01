package co.eci.arep.service;

import co.eci.arep.security.UrlReaderSecure;
import spark.Request;
import spark.Response;
public class LoginService {

    //private final String URL_SERVICE = "https://localhost:4500";
    private final String URL_SERVICE = "https://ec2-54-174-68-216.compute-1.amazonaws.com:4500";


    /**
     * Method that authenticates an user
     * @param req - request with the credentials required
     * @param res - response HTTP
     * @return Success or failure message
     */
    public Object authenticate(Request req, Response res) {
        System.out.println("Request to authenticate user received!");
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        String response;

        if (username != null && password != null) {
            res.status(200);
            response = UrlReaderSecure
                    .readURL(URL_SERVICE+"/auth?username=" + username + "&password=" + password);
        } else {
            res.status(400);
            response = "BAD REQUEST!";
        }

        return response;
    }
}
