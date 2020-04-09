package com.hwork.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http//.addFilterBefore(verificationCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//                .httpBasic()
                .loginPage("/authentication/required")
                .loginProcessingUrl("/authentication/form")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and().authorizeRequests()
                .antMatchers(securityProperties.getBrowser().getNotAuthList().split(","))
                .permitAll()
                .anyRequest()
                .authenticated();
//                .hasRole("USER");
        http.csrf().disable();
    }*/

    @Override
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
