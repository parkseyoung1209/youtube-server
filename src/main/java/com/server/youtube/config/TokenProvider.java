package com.server.youtube.config;

import com.server.youtube.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Service
public class TokenProvider {

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

            public String create (Member member) {
                return Jwts.builder()
                        .signWith(secretKey)
                        .setClaims(Map.of(
                                "id", member.getId(),
                                "email", member.getEmail()
                        ))
                        .setIssuedAt(new Date())
                        .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                        .compact();
            }
     public Member validate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Member.builder()
                .id((String) claims.get("id"))
                .email((String) claims.get("email"))
                .build();
     }
}
