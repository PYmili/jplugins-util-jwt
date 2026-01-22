package org.jplugins.util.properties;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "jplugins.util.jwt")
public class JpluginsUtilJwtProperties {

    /**
     * 默认主题
     */
    private String subject = "default";

    /**
     * 过期时间（毫秒）
     */
    private long expireMillis = 3600 * 1000;

    /**
     * 存储载荷的键
     */
    private String payloadKey = "JwtPayload";

    /**
     * 密钥类型
     * HS256 - 32（默认）
     * HS384 - 48
     * HS512 - 64
     */
    private int secretKeyType = 32;

    /**
     * 密钥
     */
    private String secretKey;

    public SecretKey getSecretKey() {
        // 判断密钥字符串是否合法
        if (!StringUtils.hasText(secretKey)) {
            throw new IllegalArgumentException(
                    "JWT secretKey must be configured in application.properties");
        }

        // 判断密钥长度要求
        if (secretKey.length() < secretKeyType) {
            throw new IllegalArgumentException(
                    String.format("JWT secretKey must be at least " +
                            "%d characters long", secretKeyType));
        }

        // 转换为Secret Key对象
        byte[] keyStrBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyStrBytes);
    }
}
