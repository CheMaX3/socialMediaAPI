//package org.chemax.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.chemax.entity.User;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//    @Value("${jwt.expiration}")
//    private String expirationTime;
//
//    public String extractUsername(String authToken) {
//        return getClaimsFromToken(authToken)
//                .getSubject();
//    }
//
//    public boolean validateToken(String authToken) {
//        return getClaimsFromToken(authToken)
//                .getExpiration()
//                .before(new Date());
//    }
//
//    public Claims getClaimsFromToken(String authToken) {
//        String key = Base64.getEncoder().encodeToString(secret.getBytes());
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(authToken)
//                .getBody();
//    }
//
//    public String generateToken(User user) {
//        HashMap<String, Object> claims = new HashMap<>();
//        claims.put("role", List.of(user.getRoles()));
//
//        Long expirationSeconds = Long.parseLong(expirationTime);
//        Date creationDate = new Date();
//        Date expirationDate = new Date(creationDate.getTime() + expirationSeconds * 1000);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(user.getUsername())
//                .setIssuedAt(creationDate)
//                .setExpiration(expirationDate)
//                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .compact();
//    }
//}
