package com.example.demo.Security;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a secure random 256-bit key
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Generated Secret Key: " + base64Key);
    }
}
