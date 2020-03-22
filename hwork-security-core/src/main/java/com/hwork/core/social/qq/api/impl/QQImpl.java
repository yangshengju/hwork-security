package com.hwork.core.social.qq.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwork.core.social.qq.api.QQ;
import com.hwork.core.social.qq.api.QQUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * Created by yangshengju on 2019-7-17.
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    Logger logger = LoggerFactory.getLogger(QQImpl.class);

    private static final String URL_GET_OPENID="https://graph.qq.com/oauth2.0/me?access_token=YOUR_ACCESS_TOKEN=%s";

    private static final String URL_GET_USERINFO="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 应用在QQ系统注册的Id
     */
    private String appId;

    private String openId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String urlGetOpenId = String.format(URL_GET_OPENID,accessToken);
        String result = getRestTemplate().getForObject(urlGetOpenId,String.class);
        logger.info("result for get OpenId : "+result);
        this.openId= StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String urlGetUserInfo = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(urlGetUserInfo,String.class);
        logger.info("result for get UserInfo : "+result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
