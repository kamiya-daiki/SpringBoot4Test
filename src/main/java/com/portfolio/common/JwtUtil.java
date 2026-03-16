package com.portfolio.common;

import java.util.Date;
import java.security.Key;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // private final Key key =
    //     Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // public String generateToken(String username) {
    //     return Jwts.builder()
    //             .setSubject(username)
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + 86400000))
    //             .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
    //             .compact();
    // }

     private final Key key =
        Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(
                new Date(System.currentTimeMillis() + 86400000)
            )
            .signWith(key)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                //.setSigningKey(key.getBytes())
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}