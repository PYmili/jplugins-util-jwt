package org.jplugins.util.jwt;

import lombok.Builder;
import lombok.Data;
import org.jplugins.util.properties.JpluginsUtilJwtProperties;

import javax.crypto.SecretKey;

/**
 * JWT Token配置构建器
 * 支持通过配置文件设置默认值
 */
@Data
@Builder
public class JwtBuilder {

    /**
     * 主题
     */
    private String subject;

    /**
     * 载荷
     */
    private JwtPayload payload;

    /**
     * 密钥
     */
    private SecretKey secretKey;

    /**
     * 过期时间（毫秒）
     */
    private Long expireMillis;

    /**
     * 使用配置属性创建带默认值的构建器
     * 当字段未显式设置时，自动使用配置文件中的值
     *
     * @param properties JWT配置属性
     * @return 配置构建器实例
     */
    public static JwtBuilder withDefaults(JpluginsUtilJwtProperties properties) {
        return JwtBuilder.builder()
                .subject(properties.getSubject())
                .expireMillis(properties.getExpireMillis())
                .secretKey(properties.getSecretKey())
                .build();
    }

    /**
     * 创建构建器并填充默认值
     * 如果当前实例字段为null，则使用配置属性中的值
     *
     * @param properties JWT配置属性
     * @return 填充默认值后的构建器
     */
    public JwtBuilder withFallbackDefaults(JpluginsUtilJwtProperties properties) {
        if (this.subject == null) {
            this.subject = properties.getSubject();
        }
        if (this.expireMillis == null) {
            this.expireMillis = properties.getExpireMillis();
        }
        if (this.secretKey == null) {
            this.secretKey = properties.getSecretKey();
        }
        return this;
    }
}
