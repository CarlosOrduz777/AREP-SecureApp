package co.eci.arep.security;
import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
public class UrlReaderSecure {

    public static void initSecureContext() {
        try {

            // Create a file and a password representation
            File trustStoreFile = new File("keys/myTrustStore");
            char[] trustStorePassword = "sanord$20".toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the trustStore object
            tmf.init(trustStore);

            // Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

            // Now, we can now read URLs

        } catch (KeyStoreException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(UrlReaderSecure.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Method that reads the content of the result of a GET request  in the authentication co.eci.arep.service
     * @param sitetoread - url of the endpoint with params of authentication
     * @return la respuesta y contenido de authService
     */
    public static String readURL(String sitetoread) {
        System.out.println("This is the site to read: "+sitetoread);
        try {
            System.out.println("Reading URL: " + sitetoread);
            // Crea el objeto que representa una URL2
            URL siteURL = new URL(sitetoread);
            // Crea el objeto que URLConnection
            URLConnection urlConnection = siteURL.openConnection();
            // Obtiene los campos del encabezado y los almacena en un estructura Map
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            // Obtiene una vista del mapa como conjunto de pares <K,V>
            // para poder navegarlo
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            // Recorre la lista de campos e imprime los valores
            for (Map.Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();

                // Si el nombre es nulo, significa que es la linea de estado
                if (headerName != null) {
                    System.out.print(headerName + ":");
                }
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    System.out.print(value);
                }
                System.out.println("");
            }

            System.out.println("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine.trim());
            }
            reader.close();
            return response.toString();

        } catch (IOException x) {
            System.err.println(x);
        }
        return "Forbidden, check your credentials!";
    }
}
