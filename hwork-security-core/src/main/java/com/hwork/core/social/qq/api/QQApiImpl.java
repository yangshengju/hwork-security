package com.hwork.core.social.qq.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * Created by yangshengju on 2019-7-17.
 */
public class QQApiImpl extends AbstractOAuth2ApiBinding implements IQQApi {

    Logger logger = LoggerFactory.getLogger(QQApiImpl.class);

    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN=%s";

    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    /**
     * 应用在QQ系统注册的Id
     */
    private String appId;

    private String openId;

    public QQApiImpl(String accessToken,String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String urlGetOpenId = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(urlGetOpenId,String.class);
        logger.info("result for get OpenId : "+result);
        this.openId= StringUtils.substringBetween(result,"\"openid\":","}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO,appId,openId);
        return null;
    }
}
