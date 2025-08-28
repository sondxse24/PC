package com.pc.utils;

import com.pc.enums.Role;
import com.pc.model.Users;
import com.pc.service.UserSer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {

    @Autowired
    private UserSer userSer;

    private final String SECRET;
    private final long EXPIRATION_TIME;
    private final long REFRESH_EXPIRATION_TIME;

    public JwtUtils(
            @Value("${jwt.secret}") String SECRET,
            @Value("${jwt.expiration}") long EXPIRATION,
            @Value("${jwt.refresh.expiration}") long REFRESH_EXPIRATION_TIME) {
        this.SECRET = SECRET;
        this.EXPIRATION_TIME = EXPIRATION;
        this.REFRESH_EXPIRATION_TIME = REFRESH_EXPIRATION_TIME;
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(Map<String, Object> claims, String subject, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(String email, List<String> roles, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>(extraClaims);
        claims.put("roles", roles);
        return createToken(claims, email, EXPIRATION_TIME);
    }

    public String generateRefreshToken(String email) {
        return createToken(new HashMap<>(), email, REFRESH_EXPIRATION_TIME);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String extractSubject(String token) { // email/username
        return extractAllClaims(token).getSubject();
    }

    public String extractJti(String token) {
        return extractAllClaims(token).getId();
    }

    public Role getRoleByJwt(String token) {
        Claims claims = extractAllClaims(token);
        String role = claims.get("role", String.class);
        return Role.valueOf(role);
    }
}
