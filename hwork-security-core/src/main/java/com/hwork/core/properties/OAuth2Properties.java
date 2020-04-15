package com.hwork.core.properties;

import lombok.Data;

@Data
public class OAuth2Properties {
    private String signingKey = "hworkSecurityJwtSigningKey";
    private OAuth2ClientProperties[] clients = {};
}
