package bank_management;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PinEncryptor {

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPin(String pin, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashed = md.digest(pin.getBytes());
        return Base64.getEncoder().encodeToString(hashed);
    }

    public static boolean verifyPin(String inputPin, String storedHash, String salt) throws Exception {
        String inputHash = hashPin(inputPin, salt);
        return inputHash.equals(storedHash);
    }
}