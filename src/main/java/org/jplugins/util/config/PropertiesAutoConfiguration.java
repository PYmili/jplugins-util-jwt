package org.jplugins.util.config;

import org.jplugins.util.jwt.JwtUtil;
import org.jplugins.util.properties.JpluginsUtilJwtProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 对 {@link JwtUtil} 的配置类进行自动装配
 * @author PYmili
 */
@Configuration
@ConditionalOnClass(JpluginsUtilJwtProperties.class)
@EnableConfigurationProperties(JpluginsUtilJwtProperties.class)
public class PropertiesAutoConfiguration {

}
