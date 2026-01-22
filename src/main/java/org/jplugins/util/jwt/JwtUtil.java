package org.jplugins.util.jwt;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

public interface JwtUtil {

    String generation();

    String generation(JwtBuilder builder);

    Claims parse(String token);

    Claims parse(String token, SecretKey key);

    JwtPayload getPayload(String token);

    JwtPayload getPayload(String token, SecretKey key);

    boolean isTokenValid(String token);

}
