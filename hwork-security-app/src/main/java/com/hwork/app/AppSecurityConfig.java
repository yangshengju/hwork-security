package com.hwork.app;

import com.hwork.core.properties.SecurityProperties;
import com.hwork.core.verificationcode.VerificationCodeValidateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author yangs
 */
@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final AuthenticationFailureHandler customAuthenticationFailureHandler;

    private final UserDetailsService hworkUerDetailsService;

    private final PersistentTokenRepository tokenRepository;

    public AppSecurityConfig(SecurityProperties securityProperties, AuthenticationSuccessHandler customAuthenticationSuccessHandler, AuthenticationFailureHandler customAuthenticationFailureHandler, UserDetailsService hworkUerDetailsService, PersistentTokenRepository tokenRepository) {
        this.securityProperties = securityProperties;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.hworkUerDetailsService = hworkUerDetailsService;
        this.tokenRepository = tokenRepository;
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        VerificationCodeValidateFilter verificationCodeValidateFilter = new VerificationCodeValidateFilter(this.securityProperties,this.customAuthenticationFailureHandler);
        verificationCodeValidateFilter.afterPropertiesSet();
        http.addFilterBefore(verificationCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .httpBasic()
                .loginPage("/authentication/required")
                .loginProcessingUrl("/authentication/form")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(tokenRepository)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(hworkUerDetailsService)
//                .and()
//                .sessionManagement()
//                .invalidSessionUrl("/session/invalid")
                .and()
                .authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getNotAuthList().split(","))
                .permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
