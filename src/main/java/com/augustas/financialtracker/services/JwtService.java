package com.augustas.financialtracker.services;

import com.augustas.financialtracker.entities.Users;
import com.augustas.financialtracker.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JwtService {
    private final UserRepository userRepository;
    private final CustomUserDetailsService userDetails;

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    public String generateToken(Users user){
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtExpiration * 1000)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String authToken) {
         return Jwts.parser()
                .setSigningKey(getSigningKey())
                 .parseClaimsJws(authToken)
                 .getBody();


    }

    public String extractUsername(String authToken){
        var claims = extractClaims(authToken);

        return claims.getSubject();
    }
    public boolean isExpired(String authToken){
        Date expiresAt =  extractClaims(authToken).getExpiration();

        return expiresAt.before(new Date());

    }

    public boolean isTokenValid(String token, UserDetails user){

        var email = extractUsername(token);
        if(email.equals(user.getUsername())){
            return !isExpired(token);
        }

        return false;
    }

    private Key getSigningKey() {
        byte[] keyBytes = key.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
