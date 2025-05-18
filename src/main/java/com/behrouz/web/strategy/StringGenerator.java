package com.behrouz.web.strategy;

import java.util.Date;
import java.util.Random;

/**
 * Created By Hapi KZM
 * Package ir.mobintabaran.xima.server.strategy
 * Project xima
 * 13 September 2018 10:59
 **/
public class StringGenerator {

    private static String generate(int tokenLength, String characters) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < tokenLength) {
            int index = (int) (rnd.nextFloat() * characters.length());
            salt.append(characters.charAt(index));
        }
        return salt.toString();
    }

    private static String generate(int tokenLength) {
        return generate(tokenLength, "abcdefghijklmnopqrstuvwxyz1234567890");
    }

    public static String generateDigit(int tokenLength) {
        return generate(tokenLength, "1234567890");
    }

    public static String generatePassword() {
        return generate(8);
    }

    public static String generateToken() {
        return generate(30);
    }

    public static String generateCookie() {
        return "mt-xm-" + generate(32) + new Date().getTime() + generate(8);
    }

    public static String generateSalt() {
        return generate(64);
    }

}
