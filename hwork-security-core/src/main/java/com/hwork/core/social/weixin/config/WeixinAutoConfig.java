package com.hwork.core.social.weixin.config;

import com.hwork.core.properties.SecurityProperties;
import com.hwork.core.properties.WeixinProperties;
import com.hwork.core.social.weixin.connect.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
// 当配置了app-id的时候才启用
@ConditionalOnProperty(prefix = "hwork.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
        ConnectionFactory connectionFactory = new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
        connectionFactoryConfigurer.addConnectionFactory(connectionFactory);
    }

/*
    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new ImoocConnectView();
    }
*/
}
