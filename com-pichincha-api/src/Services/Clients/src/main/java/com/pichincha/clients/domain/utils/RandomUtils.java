package com.pichincha.clients.domain.utils;

import java.security.SecureRandom;

public class RandomUtils {

    private RandomUtils() {

        throw new IllegalStateException("RandomUtils class");
    }

    public static String generateRandomString(int length) {

        String charLower = "abcdefghijklmnopqrstuvwxyz";
        String charUpper = charLower.toUpperCase();
        String number = "0123456789";

        String dataForRandom = charLower + charUpper + number;
        SecureRandom random = new SecureRandom();

        if (length < 1) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            int rndCharAt = random.nextInt(dataForRandom.length());
            char rndChar = dataForRandom.charAt(rndCharAt);

            sb.append(rndChar);
        }

        return sb.toString();
    }
}
