package com.app.phonecontacts.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private static final String SECRET_KEY
            = "EgDM8XhXJd00DX4xCHV3Tj21OQuTskA6DLXrhtklKJHSQn8W2QoBm/x3sUIx+Yr7nQ5bkl1ewcYUjF1i9nW4jQ==";

    public String generateToken(String login) {
        Date date = Date.from(LocalDateTime.now().plusSeconds(30).plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                .builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token) {
        getClaims(token);
        return true;
    }

    public String getLoginFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
