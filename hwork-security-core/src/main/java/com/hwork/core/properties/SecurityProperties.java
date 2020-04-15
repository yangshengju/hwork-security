package com.hwork.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yangs
 */
@ConfigurationProperties(prefix = "hwork.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social;

    private ValidateCodeProperties code;

    private OAuth2Properties oauth2 = new OAuth2Properties();

}
