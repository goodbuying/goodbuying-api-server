package goodbuyning.api_server.infra.generator;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenGenerator {
    
    private static final String JWT_SECRET = "your-secret-key-here-make-it-long-enough-for-hmac-sha256";
    private static final long JWT_EXPIRATION = 86400000; // 24 hours
    
    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }
    
    public static String generateAccessToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }
    
    public static String generateRefreshToken(String userId) {
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION * 7))
                .signWith(getSigningKey())
                .compact();
    }
}
