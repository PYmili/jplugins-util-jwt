package org.jplugins.util.jwt;

import io.jsonwebtoken.Claims;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Jwt Claims 工具类
 * @author PYmili
 */
public class JwtClaimsUtil {

    /**
     * 将 {@link Instant} 转换为系统默认时区的 {@link LocalDateTime}
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    private LocalDateTime toLocalDateTime(Instant instant) {
        // 当前系统的时区
        ZoneId zoneId = ZoneId.systemDefault();
        // 转换为LocalDateTime
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取 {@link Claims} 中的创建时间
     * @param claims {@link Claims}
     * @return {@link LocalDateTime}
     */
    public LocalDateTime getIssuedAt(Claims claims) {
        return toLocalDateTime(claims.getIssuedAt().toInstant());
    }

    /**
     * 获取 {@link Claims} 中的过期时间
     * @param claims {@link Claims}
     * @return {@link LocalDateTime}
     */
    public LocalDateTime getExpiration(Claims claims) {
        return toLocalDateTime(claims.getExpiration().toInstant());
    }

    /**
     * 用于打印和解析日期时间对象的Formatter的ofPattern方法
     * @param localDateTime {@link LocalDateTime}
     * @param pattern {@link String}
     * @return {@link String}
     */
    private String ofPattern(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    private String ofPattern(LocalDateTime localDateTime) {
        return ofPattern(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取 {@link Claims} 中的创建时间并根据Pattern进行转换
     * @param claims {@link Claims} jjwt的Claims
     * @param pattern {@link String} 转换的表达式
     * @return {@link String}
     */
    public String getIssuedAtString(Claims claims, String pattern) {
        return ofPattern(getIssuedAt(claims), pattern);
    }

    public String getIssuedAtString(Claims claims) {
        return ofPattern(getIssuedAt(claims));
    }

    /**
     * 获取 {@link Claims} 中的过期时间并根据Pattern进行转换
     * @param claims {@link Claims} jjwt的Claims
     * @param pattern {@link String} 转换的表达式
     * @return {@link String}
     */
    public String getExpirationString(Claims claims, String pattern) {
        return ofPattern(getExpiration(claims), pattern);
    }

    public String getExpirationString(Claims claims) {
        return ofPattern(getExpiration(claims));
    }

}
