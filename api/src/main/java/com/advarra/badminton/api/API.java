package com.advarra.badminton.api;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class API {

    private static final int SESSION_ID_LENGTH = 32;
    private static final String SECRET_KEY = "MZQPalskdjfhg1029384756";
    private static final String INIT_VECTOR = "1029384756MZQPalskdjfhg";

    public static String generateSessionId() {
        SecureRandom random = new SecureRandom();
        byte[] sessionIdBytes = new byte[SESSION_ID_LENGTH];
        random.nextBytes(sessionIdBytes);
        // Convert the random bytes to a hexadecimal string
        BigInteger sessionId = new BigInteger(1, sessionIdBytes);
        return sessionId.toString(16);
    }

    public static String encryptSessionId(String sessionId) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
        byte[] encrypted = cipher.doFinal(sessionId.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptSessionId(String encryptedSessionId) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes("UTF-8"));
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedSessionId));
        return new String(decrypted);
    }
}
