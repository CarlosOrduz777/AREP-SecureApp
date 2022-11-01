package co.eci.arep.controller;
import static spark.Spark.get;
import co.eci.arep.service.LoginService;
public class LoginController {

    /**
     * Method to manage the authentication of the login co.eci.arep.service
     * @param loginService co.eci.arep.service to manage
     */
    public LoginController(final LoginService loginService) {
        get("hello", (req, res) -> "Hello World, from Login Service!");
        get("auth", (req, res) -> loginService.authenticate(req, res));
    }
}
