package com.codele.demo.SERVICE.IMPL;

import com.codele.demo.SERVICE.JWTservice;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTimpl implements JWTservice {

    // Replace this with your actual securely generated Base64-encoded secret key
    private static final String SECRET_KEY = "OTNkODMyOWYyYTc3Y2Y3MTdmMDE5ODcxNTNhY2U2Mjk5OTJlNjNkNmY=";

    @Override
    public String generatetoken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshtoken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(getSignkey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public <T> T extractClaims(String token, Function<Claims, T> ClaimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return ClaimsResolvers.apply(claims);

    }

    @Override
    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Key getSignkey() {
        // Decode the Base64 secret key and create a Key object
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
