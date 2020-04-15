package com.hwork.core.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {
    private String clientId;

    private String clientSecret;

    private Integer accessTokenValiditySeconds;

    private String[] authorizedGrantTypes;

    private String[] authorities;

    private String[] redirectUris;

    private String[] scopes;
}
