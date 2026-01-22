package org.jplugins.util.jwt;

import io.jsonwebtoken.Claims;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class JwtClaimsUtil {

    private LocalDateTime toLocalDateTime(Instant instant) {
        // 当前系统的时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 转换为LocalDateTime
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public LocalDateTime getIssuedAt(Claims claims) {
        return toLocalDateTime(claims.getIssuedAt().toInstant());
    }

    public LocalDateTime getExpiration(Claims claims) {
        return toLocalDateTime(claims.getExpiration().toInstant());
    }

    private String ofPattern(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    private String ofPattern(LocalDateTime localDateTime) {
        return ofPattern(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getIssuedAtString(Claims claims, String pattern) {
        return ofPattern(getIssuedAt(claims), pattern);
    }

    public String getIssuedAtString(Claims claims) {
        return ofPattern(getIssuedAt(claims));
    }

    public String getExpirationString(Claims claims, String pattern) {
        return ofPattern(getExpiration(claims), pattern);
    }

    public String getExpirationString(Claims claims) {
        return ofPattern(getExpiration(claims));
    }

}
