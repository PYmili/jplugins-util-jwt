package org.jplugins.util.config;

import org.jplugins.util.jwt.JwtUtil;
import org.jplugins.util.jwt.impl.JwtUtilImpl;
import org.jplugins.util.properties.JpluginsUtilJwtProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * 对 {@link JwtUtil} 进行自动装配
 * @author PYmili
 */
@AutoConfiguration
@ConditionalOnMissingBean(JwtUtil.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class JwtUtilAutoConfiguration {

    @Bean
    public JwtUtil jwtUtil(JpluginsUtilJwtProperties properties) {
        return new JwtUtilImpl(properties);
    }

}
