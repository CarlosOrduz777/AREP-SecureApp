package co.edu.arep.controller;
import static spark.Spark.get;

import co.edu.arep.service.AuthService;
public class AuthController {
    /**
     * Method that manage the login of AuthService
     *
     * @param authService - service to manage
     */
    public AuthController(final AuthService authService) {
        get("hello", (req, res) -> "Hello World, from Authentication Service!");
        get("auth", (req, res) -> authService.login(req, res));
    }
}
