package com.partola.productsapij.util;

import com.partola.productsapij.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

/**
 * @author Ivan Partola
 */

public final class JwtUtil {

    private JwtUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private static final Key KEY = Keys.hmacShaKeyFor(System.getenv("SIGNATURE_KEY").getBytes());

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
        } catch (Exception e) {
            throw new InvalidTokenException(e);
        }
    }
}
