package com.myspringweb.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Date;
import java.util.Map;


public class JwtUtils {
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
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
