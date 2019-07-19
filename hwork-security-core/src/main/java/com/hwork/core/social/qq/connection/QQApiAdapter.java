package com.hwork.core.social.qq.connection;

import com.hwork.core.social.qq.api.IQQApi;
import com.hwork.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQApiAdapter implements ApiAdapter<IQQApi> {
    @Override
    public boolean test(IQQApi iqqApi) {
        return true;
    }

    @Override
    public void setConnectionValues(IQQApi iqqApi, ConnectionValues connectionValues) {
        QQUserInfo qqUserInfo = iqqApi.getUserInfo();
        connectionValues.setDisplayName(qqUserInfo.getNickname());
        connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1());
        connectionValues.setProviderUserId(qqUserInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(IQQApi iqqApi) {
        return null;
    }

    @Override
    public void updateStatus(IQQApi iqqApi, String s) {

    }
}
