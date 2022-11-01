package co.eci.arep;

import static spark.Spark.port;
import static spark.Spark.secure;

import co.eci.arep.controller.LoginController;
import co.eci.arep.security.UrlReaderSecure;
import co.eci.arep.service.LoginService;
public class SparkWeb {



    /**
     * co.eci.arep.SparkWeb manage the login user in a secure context.
     *
     * @author Carlos Orduz
     */
    public static void main(String[] args) {
        port(getPort());

        // API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,
        // truststorePassword);
        secure("keys/ecikeystore.p12", "sanord$20", null, null);
        UrlReaderSecure.initSecureContext();

        new LoginController(new LoginService());
    }

    /**
     * get the port of the environment variables or the default port.
     *
     * @return el puerto
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
