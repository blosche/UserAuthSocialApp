package com.tco.misc;
import java.security.SecureRandom;
import java.lang.Byte;
import java.util.Base64;

public class SessionTokenGenerator {
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private SessionTokenGenerator() {

    }
    public static String createToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        return base64Encoder.encodeToString(randomBytes);
    }
}
