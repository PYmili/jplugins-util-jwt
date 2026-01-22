package org.jplugins.util.config;

import org.jplugins.util.properties.JpluginsUtilJwtProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(JpluginsUtilJwtProperties.class)
@EnableConfigurationProperties(JpluginsUtilJwtProperties.class)
public class PropertiesAutoConfiguration {

}
