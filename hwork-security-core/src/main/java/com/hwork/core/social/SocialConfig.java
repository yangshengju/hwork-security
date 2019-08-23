package com.hwork.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource druidDataSource;
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        UsersConnectionRepository usersConnectionRepository = new JdbcUsersConnectionRepository(druidDataSource,connectionFactoryLocator, Encryptors.noOpText());
        ((JdbcUsersConnectionRepository) usersConnectionRepository).setTablePrefix("t_");
        return usersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer hworkSocialSecurityConfig() {
        return new SpringSocialConfigurer();
    }
}
