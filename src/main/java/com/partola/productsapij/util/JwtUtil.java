package com.partola.productsapij.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
/**
 * @author Ivan Partola
 */

public class JwtUtil {
    private JwtUtil() {}
    private static final Key key = Keys.hmacShaKeyFor(System.getenv("SIGNATURE_KEY").getBytes());

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
