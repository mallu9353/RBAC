package com.rbac.util;



import io.jsonwebtoken.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.rbac.entities.User;

import java.time.Instant;
import java.util.Date; // Still use java.util.Date internally for JWT API compatibility
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secretKey = "yourSecretKey"; // Replace with your secret key
    private final long jwtExpirationInMs = 3600000; // Token validity (1 hour in milliseconds)

    // Generate a JWT token
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user);
    }

    private String createToken(Map<String, Object> claims, User user) {
		// TODO Auto-generated method stub
		return "Token";
	}

	// Create token with claims
    private String createToken(Map<String, Object> claims, String user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Using java.util.Date
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)) // Using java.util.Date
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from token
    public java.sql.Date extractExpiration(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return new java.sql.Date(expiration.getTime()); // Convert to java.sql.Date
    }

    // Generic method to extract a specific claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Validate the token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails) && !isTokenExpired(token));
    }

    // Check if the token has expired
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new java.sql.Date(System.currentTimeMillis()));
//    }

    private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	// Parse and extract all claims from the token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token has expired", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Unsupported JWT", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Malformed JWT", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid signature", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Illegal argument token", e);
        }
    }
}



