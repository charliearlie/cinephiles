/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinephiles.security;

import cinephiles.security.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author gareth
 */
public class RandomStringGenerator {
    public static final char[] STRING_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

    SecureRandom sr;

    public RandomStringGenerator() throws RuntimeException {
        try {
            sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Failed to instantiate key handler class\n"
                    + ex.getMessage());
        }
    }

    public String generateRandomString(int KeyLength) {
        StringBuilder out = new StringBuilder(KeyLength);
        for (int i = 0; i < KeyLength; i++) {
            out.append(STRING_ALPHABET[sr.nextInt(STRING_ALPHABET.length)]);
        }
        return out.toString();
    }

}
