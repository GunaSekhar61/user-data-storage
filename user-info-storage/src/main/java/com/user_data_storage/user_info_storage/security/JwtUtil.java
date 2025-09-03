package com.user_data_storage.user_info_storage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final  long expirationMs;

    public JwtUtil(@Value("${jwt.secret}") String base64Secret, @Value("${jwt.expiration.ms}") long expirationMs) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expirationMs = expirationMs;
    }

    public String generateToken(String username){
        Date now=new Date();
        Date exp=new Date(now.getTime()+expirationMs);
        return Jwts.builder()
                .subject(username)
                .issuer("data-storage")
                .issuedAt(now)
                .expiration(exp)
                .claim("role","ADMIN")
                .signWith(key)
                .compact();
    }

    public boolean isTokenValid(String token, String username){
        try{
            final Claims claims= parseClaims(token).getPayload();
            return claims.getSubject().equals(username) && !claims.getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public String extractUsername(String token){
        Claims claims = parseClaims(token).getPayload();
        return claims.getSubject();
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parser().setSigningKey(key).build().parseSignedClaims(token);
    }



}
