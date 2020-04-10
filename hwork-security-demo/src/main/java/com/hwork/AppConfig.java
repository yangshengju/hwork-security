package com.hwork;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
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
    /**
    * 绑定资源属性
    */
    @Value("${hwork.datasource.driverClassName}")
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

}
