package com.hwork.core.social.qq.config;

import com.hwork.core.properties.QQProperties;
import com.hwork.core.properties.SecurityProperties;
import com.hwork.core.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
@Configuration
// 当配置了app-id的时候才启用
@ConditionalOnProperty(prefix = "hwork.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        ConnectionFactory connectionFactory = new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
        connectionFactoryConfigurer.addConnectionFactory(connectionFactory);
    }
}
