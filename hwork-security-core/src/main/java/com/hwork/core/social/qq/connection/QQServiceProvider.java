package com.hwork.core.social.qq.connection;

import com.hwork.core.social.qq.api.IQQApi;
import com.hwork.core.social.qq.api.QQApiImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<IQQApi> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEM="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEM));
        this.appId=appId;
    }

    @Override
    public IQQApi getApi(String accessToken) {
        return new QQApiImpl(accessToken,appId);
    }
}
