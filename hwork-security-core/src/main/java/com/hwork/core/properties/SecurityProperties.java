package com.hwork.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hwork.security")
@Data
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social;

}
