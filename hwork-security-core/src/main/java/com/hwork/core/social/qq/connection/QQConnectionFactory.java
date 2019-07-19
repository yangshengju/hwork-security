package com.hwork.core.social.qq.connection;

import com.hwork.core.social.qq.api.IQQApi;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

public class QQConnectionFactory extends OAuth2ConnectionFactory<IQQApi> {
    public QQConnectionFactory(String providerId, String appId,String appSecret) {
        super(providerId, new QQServiceProvider(appId,appSecret), new QQApiAdapter());
    }
}
