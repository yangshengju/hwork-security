package com.hwork.core.properties;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
