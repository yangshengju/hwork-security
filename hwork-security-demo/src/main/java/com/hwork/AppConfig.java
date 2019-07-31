package com.hwork;

import com.hwork.filter.TimeFilter;
import com.hwork.interceptor.TimeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Configuration
public class AppConfig implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).excludePathPatterns("/*.html");
    }
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urlPatthers = new ArrayList();
        urlPatthers.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatthers);
        return filterRegistrationBean;
    }

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)//Docket, Springfox’s, primary api configuration mechanism is initialized for swagger specification 2.0
        .select()//returns an instance of ApiSelectorBuilder to give fine grained control over the endpoints exposed via swagger.
        .apis(RequestHandlerSelectors.any())//apis() allows selection of RequestHandler's using a predicate
        .paths(PathSelectors.any())//allows selection of Path's using a predicate
        .build()//The selector needs to be built after configuring the api and path selectors.
        .pathMapping("/")//Adds a servlet path mapping, when the servlet has a path mapping. This prefixes paths with the provided path mapping.
        .directModelSubstitute(LocalDate.class,String.class)//Convenience rule builder that substitutes LocalDate with String when rendering model properties
        .genericModelSubstitutes();
    }
}
