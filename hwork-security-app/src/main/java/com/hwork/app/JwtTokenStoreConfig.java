package com.hwork.app;

import com.hwork.app.jwt.HworkJwtTokenEnhancer;
import com.hwork.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@ConditionalOnProperty(prefix="hwork.security.oauth2",name="storeType",havingValue="jwt",matchIfMissing = true)
public class JwtTokenStoreConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getSigningKey());
        return jwtAccessTokenConverter;
    }

    @Bean
    @ConditionalOnMissingBean(name="jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer() {
        return new HworkJwtTokenEnhancer();
    }
}
