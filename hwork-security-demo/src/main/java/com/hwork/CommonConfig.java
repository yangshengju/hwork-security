package com.hwork;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * Created by yangshengju on 2018-8-13.
 */
public class CommonConfig {

    private static CommonConfig _commonConfig = null;

    private CommonConfig() {
    }

    /**
     * 构建数据源
     * @param ds
     * @param driverClass
     * @param url
     * @param userName
     * @param passWord
     */
    public void constructDataSource(DruidDataSource ds,String driverClass, String url, String userName, String passWord) {
        /*
        * 基本属性
        */
        ds.setDriverClassName(driverClass);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(passWord);
        /*
        * 配置初始化大小、最小、最大
        */
        ds.setInitialSize(50);
        ds.setMinIdle(30);
        ds.setMaxActive(100);
        /*!-- 配置获取连接等待超时的时间 */
//        ds.setMaxWait(60000);
        /**!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
//        ds.setTimeBetweenEvictionRunsMillis(60000);
        /*!-- 配置一个连接在池中最小生存的时间，单位是毫秒*/
//        ds.setMinEvictableIdleTimeMillis(300000);

//        ds.setTestWhileIdle(true);
//        ds.setTestOnBorrow(false);
//        ds.setTestOnReturn(false);

        /*
        * 打开PSCache，并且指定每个连接上PSCache的大小
        */
        /*if(driverClass.contains("OracleDriver")) {
            ds.setValidationQuery(" SELECT 1 FROM dual ");
            ds.setPoolPreparedStatements(true);
            ds.setMaxPoolPreparedStatementPerConnectionSize(20);
        } else {
            ds.setValidationQuery(" SELECT 1 ");
            ds.setPoolPreparedStatements(false);
        }

        try {
            ds.setFilters("stat");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 获取单例
     * @return
     */
    public static CommonConfig getCommonConfigInstance() {
        if(_commonConfig==null) {
            _commonConfig = new CommonConfig();
        }
        return _commonConfig;
    }

    /**
     * 获取session工厂
     * @param druidDataSource
     * @return
     */
    /*public SqlSessionFactory getSessionFactory(DruidDataSource druidDataSource) {
        String configLocation = "mybatis-config.xml";
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        SqlSessionFactory sqlSessionFactory = null;
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        Resource resource = new ClassPathResource(configLocation);
        sqlSessionFactoryBean.setConfigLocation(resource);
        try {
            sqlSessionFactory=sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }*/
}
