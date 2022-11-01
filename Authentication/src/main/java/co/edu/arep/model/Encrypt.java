package co.edu.arep.model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encrypt {

    /**
     * Method that cypher in a hash SHA-256 the password of the user
     * @param password - password without cypher
     * @return password encrypted
     */
    public static String encryptPasswdSHA256(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        byte[] hash = messageDigest.digest(password.getBytes());
        StringBuilder stringBuffer = new StringBuilder();

        for (byte b : hash) {
            stringBuffer.append(String.format("%02x", b));
        }

        return stringBuffer.toString();
    }
}
