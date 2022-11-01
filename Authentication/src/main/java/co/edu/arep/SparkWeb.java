package co.edu.arep;

import static spark.Spark.port;
import static spark.Spark.secure;

import co.edu.arep.controller.AuthController;
import co.edu.arep.service.AuthService;
public class SparkWeb {


    public static void main(String[] args) {
        port(getPort());
        // pasar a desplegar

        secure("keys/ecikeystore.p12", "sanord$20", null, null);

        new AuthController(new AuthService());
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4500;
    }
}
