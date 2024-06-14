package com.myspringweb.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static String key = "HsJwt";
    private static Long expire = 43200000L;

    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, key)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        if (jwt == null || jwt.trim().isEmpty()) {
            logger.error("JWT String argument cannot be null or empty.");
            throw new IllegalArgumentException("JWT String argument cannot be null or empty.");
        }
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            logger.error("Failed to parse JWT: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to parse JWT", e);
        }
    }
}
