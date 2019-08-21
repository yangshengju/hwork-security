package com.hwork.browser;

import com.hwork.core.properties.SecurityProperties;
import com.hwork.core.verificationcode.VerificationCodeValidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer hworkSocialSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        VerificationCodeValidateFilter verificationCodeValidateFilter = new VerificationCodeValidateFilter();
        verificationCodeValidateFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        http.addFilterBefore(verificationCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/required")
                .loginProcessingUrl("/authentication/form")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getNotAuthList().split(","))
                .permitAll()
                .anyRequest()
                .authenticated().and()
        .apply(hworkSocialSecurityConfig);
        http.csrf().disable();
    }
}
