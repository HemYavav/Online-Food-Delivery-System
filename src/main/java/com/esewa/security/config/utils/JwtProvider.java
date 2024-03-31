package com.esewa.security.config.utils;

import com.esewa.security.config.utils.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.*;

@Service
public class JwtProvider {

    public static String secretKeyGenerator(){
        // Generate a 256-bit (32-byte) random key
        byte[] keyBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);

        // Encode the key bytes using Base64 encoding
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);

        // Print the generated key
        System.out.println("Generated Secret Key: " + base64Key);
        return base64Key;
    }




    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        String jwt = Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000)) // 1hour
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
        return jwt;
    }
/*    public String getEmailFromJwtToken(String jwt) {
        jwt = jwt.substring(JwtConstant.AUTH_HEADER_START_WITH_SIZE);
        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }*/


    public String getEmailFromJwtToken(String jwt) {
        try {
            jwt = jwt.substring(JwtConstant.AUTH_HEADER_START_WITH_SIZE);
            Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
            return String.valueOf(claims.get("email"));
        } catch (SignatureException e) {
            // Signature verification failed
            System.err.println("JWT signature verification failed: " + e.getMessage());
            return null;
        } catch (JwtException e) {
            // Other JWT parsing exceptions
            System.err.println("JWT parsing failed: " + e.getMessage());
            return null;
        }
    }





    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}
