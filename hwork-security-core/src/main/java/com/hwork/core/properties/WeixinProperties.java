package com.hwork.core.properties;

import lombok.Data;

@Data
public class WeixinProperties {
    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;
    private String providerId = "weixin";
}
