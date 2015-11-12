/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinephiles.security;

import cinephiles.security.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author gareth
 */
public class PasswordHandler {
    public static final int ENC_INTERATIONS = 1000;
    public static final int ENC_SALT_LENGTH = 32;
    public static final int ENC_KEY_LENGTH = 256;
    public static final String HASH_SEPERATOR = ":";

    private final RandomStringGenerator randomStringGenerator;

    public PasswordHandler() throws RuntimeException {
        randomStringGenerator = new RandomStringGenerator();
    }

    private String hashPasswordUsingSalt(String password, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();

        PBEKeySpec keySpec = new PBEKeySpec(chars,
                salt.getBytes(),
                ENC_INTERATIONS,
                ENC_KEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
        return String.format("%x" + HASH_SEPERATOR + "%s",
                new BigInteger(1, hash), // Hash must be positive, so set the sign bit to positive
                salt
        );
    }

    public String hashPasswordWithRandomSalt(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String randomSalt = generateRandomSalt();

        return hashPasswordUsingSalt(password,
                randomSalt);
    }

    public boolean checkPassword(String saltedPassword, String plainTextPassword) throws RuntimeException {
        String newSaltedPassword = "";

        try {
            String[] saltedPasswordTokens = saltedPassword.split(HASH_SEPERATOR);
            if (saltedPasswordTokens.length >= 2) {
                String salt = saltedPasswordTokens[1];
                newSaltedPassword = hashPasswordUsingSalt(plainTextPassword, salt);
            }
            else
            {
                throw new RuntimeException("Salted password is in an invalid format!");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException("Unable to check password:\n"
                    + ex.getMessage());
        }

        return saltedPassword.equals(newSaltedPassword);
    }

    private String generateRandomSalt() {
        return randomStringGenerator.generateRandomString(ENC_SALT_LENGTH);
    }
}
