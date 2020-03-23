package com.hwork.app;

import com.hwork.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//.addFilterBefore(verificationCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .loginPage("/authentication/required")
//                .loginProcessingUrl("/authentication/form")
//                .successHandler(customAuthenticationSuccessHandler)
//                .failureHandler(customAuthenticationFailureHandler)
                .and().authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getNotAuthList().split(","))
                .permitAll()
                .anyRequest().hasRole("USER");
    }
}
