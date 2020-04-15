package com.hwork.app;

import com.hwork.core.properties.OAuth2ClientProperties;
import com.hwork.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class HworkAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore redisTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        for (OAuth2ClientProperties client:securityProperties.getOauth2().getClients()
             ) {
            clients.inMemory().withClient(client.getClientId())
                    .secret(passwordEncoder.encode(client.getClientSecret()))
                    .authorizedGrantTypes(client.getAuthorizedGrantTypes())
                    .scopes(client.getScopes())
                    .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                    .authorities(client.getAuthorities())
                    .redirectUris(client.getRedirectUris());
        }

    }

    /*@Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore)
                .authenticationManager(authenticationManager);
        if(jwtAccessTokenConverter!=null) {
            endpoints.accessTokenConverter(jwtAccessTokenConverter);
        }
    }
}
