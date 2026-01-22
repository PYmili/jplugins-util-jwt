package org.jplugins.util.jwt.impl;

import io.jsonwebtoken.*;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jplugins.util.jwt.JwtPayload;
import org.jplugins.util.jwt.JwtUtil;
import org.jplugins.util.jwt.JwtBuilder;
import org.jplugins.util.properties.JpluginsUtilJwtProperties;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class JwtUtilImpl implements JwtUtil {

    @Resource
    private JpluginsUtilJwtProperties properties;

    /**
     * 生成 Secret Key
     */
    private String generationSecretKey(int size) {
        SecretKey sig = Jwts.SIG.HS256.key().build();
        if (size == 512) sig = Jwts.SIG.HS512.key().build();
        if (size == 384) sig = Jwts.SIG.HS384.key().build();
        return Base64.getEncoder().encodeToString(sig.getEncoded());
    }

    private Map<String, Object> generationClaims(JwtPayload payload) {
        if (payload == null) {
            return Map.of();
        }
        return Map.of(properties.getPayloadKey(), payload);
    }

    @Override
    public String generation(JwtBuilder builder) {
        Date date = new Date();
        long expireMillis = builder.getExpireMillis() == null ?
                properties.getExpireMillis() : builder.getExpireMillis();
        return Jwts.builder()
                .subject(!StringUtils.hasText(builder.getSubject()) ?
                        properties.getSubject() : builder.getSubject())
                .signWith(builder.getSecretKey() == null ?
                        properties.getSecretKey() : builder.getSecretKey())
                .claims(generationClaims(builder.getPayload()))
                .issuedAt(date)
                .expiration(new Date(date.getTime() + expireMillis))
                .compact();
    }

    @Override
    public String generation() {
        return generation(JwtBuilder.builder().build());
    }

    @Override
    public Claims parse(String token, SecretKey key) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return jws.getPayload();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Expired JWT", e);
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT", e);
        }
    }

    @Override
    public Claims parse(String token) {
        return parse(token, properties.getSecretKey());
    }

    public boolean isTokenValid(String token, SecretKey key) {
        if (!StringUtils.hasText(token)) return false;
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        return isTokenValid(token, properties.getSecretKey());
    }

    @Override
    public JwtPayload getPayload(String token, SecretKey key) {
        Object object = parse(token, key).get(properties.getPayloadKey());
        return object != null ? (JwtPayload) object : null;
    }

    @Override
    public JwtPayload getPayload(String token) {
        return getPayload(token, properties.getSecretKey());
    }
}
