package org.jplugins.util.jwt;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

/**
 * Jwt Token 的工具类
 * @author PYmili
 */
public interface JwtUtil {

    /**
     * 生成 Jwt Token，使用默认配置进行生成
     * @return {@link String}
     */
    String generation();

    /**
     * 生成 Jwt Token
     * @param builder {@link JwtBuilder} 生成构建器
     * @return {@link String}
     */
    String generation(JwtBuilder builder);

    /**
     * 解析 Jwt Token
     * @param token {@link String} 需要解析的 Token
     * @return {@link String}
     */
    Claims parse(String token);

    /**
     * 解析 Jwt Token
     * @param token {@link String} 需要解析的 Token
     * @param key {@link SecretKey} 自定义密钥
     * @return {@link String}
     */
    Claims parse(String token, SecretKey key);

    /**
     * 获取到自定义的载荷 {@link JwtPayload}
     * @param token {@link String} token
     * @return {@link JwtPayload}
     */
    JwtPayload getPayload(String token);

    /**
     * 获取到自定义的载荷 {@link JwtPayload}
     * @param token {@link String} token
     * @param key {@link SecretKey} 自定义密钥
     * @return {@link JwtPayload}
     */
    JwtPayload getPayload(String token, SecretKey key);

    /**
     * 检查 Jwt Token 是否可用
     * @param token {@link String} 需要检查的token
     * @return boolean
     */
    boolean isTokenValid(String token);

}
