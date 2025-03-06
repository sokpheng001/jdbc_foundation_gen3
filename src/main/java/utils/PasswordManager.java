package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordManager {
    public static String hashing(String input) {
        try{
            MessageDigest md   = MessageDigest.getInstance("SHA-256");
            byte [] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(bytes);
        }catch (Exception exception){
            System.out.println("☹Error while hashing: " + exception.getMessage());
        }
        return "Not Hashed";
    }
}
