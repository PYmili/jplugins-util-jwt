package org.jplugins.util.config;

import org.jplugins.util.jwt.JwtClaimsUtil;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * 对 {@link JwtClaimsUtil} 进行自动装配
 * @author PYmili
 */
@AutoConfiguration
@ConditionalOnMissingBean(JwtClaimsUtil.class)
@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
public class JwtClaimsUtilAutoConfiguration {

    @Bean
    public JwtClaimsUtil jwtClaimsUtil() {
        return new JwtClaimsUtil();
    }

}
