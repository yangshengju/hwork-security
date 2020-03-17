package com.hwork.core.properties;

import lombok.Data;

@Data
public class QQProperties{
    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "qq";
}
