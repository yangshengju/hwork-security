package com.hwork;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;

@Configuration
@PropertySources(value = { @PropertySource("classpath:application.properties") })
public class AppConfig implements WebMvcConfigurer {

   /* @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).excludePathPatterns("*//*.html");
    }
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urlPatthers = new ArrayList();
        urlPatthers.add("*//*");
        filterRegistrationBean.setUrlPatterns(urlPatthers);
        return filterRegistrationBean;
    }*/
   private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    /*
    * 绑定资源属性
    */
    @Value("${hwork.datasource.driver-class-name}")
    private String driverClass;

    @Value("${hwork.datasource.url}")
    private String url;

    @Value("${hwork.datasource.username}")
    private String userName;

    @Value("${hwork.datasource.password}")
    private String passWord;

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        CommonConfig.getCommonConfigInstance().constructDataSource(ds,driverClass,url,userName,passWord);
        return ds;

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
