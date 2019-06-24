package sample;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {


    //load dynamically - never actually include the salt / password in the code :-)
    private static final String SALT_CONSTANT = "12345";

    protected static String getSecurePassword(String passwordForHashing){
        String generatedPassword = null;
        try {
            //could use even stronger algorithm as needed
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT_CONSTANT.getBytes());
            byte[] bytes = md.digest(passwordForHashing.getBytes());

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
