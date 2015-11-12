/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinephiles.security;

import cinephiles.security.*;


public class KeyHandler {
    public static final int DEFAULT_KEY_LENGTH = 128;
    private final RandomStringGenerator randomStringGenerator;
    
    public KeyHandler() throws RuntimeException {
        randomStringGenerator = new RandomStringGenerator();
    }

    public String generateKey() {
        return randomStringGenerator.generateRandomString(DEFAULT_KEY_LENGTH);
    }
}
