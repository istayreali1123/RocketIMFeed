/**
 * (C) 2014-2016 Webank Group Holding Limited.
 * <p>
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License version 2 as published by the Free Software Foundation.
 */
package com.thanos.dbgate.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Component
//@EnableTransactionManagement
public class ApplicationConfigurations {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfigurations.class);

    // JDBC相关
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.user}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.initialSize}")
    private String jdbcInitialSize;

    @Value("${jdbc.minIdle}")
    private String jdbcMinIdle;

    @Value("${jdbc.maxActive}")
    private String jdbcMaxActive;

    @Value("${jdbc.maxWait}")
    private String jdbcMaxWait;

    @Value("${jdbc.timeBetweenEvictionRunsMillis}")
    private String jdbcTimeBetweenEvictionRunsMillis;

    @Value("${jdbc.minEvictableIdleTimeMillis}")
    private String jdbcMinEvictableIdleTimeMillis;

    @Value("${jdbc.validationQuery}")
    private String jdbcValidationQuery;

    @Value("${jdbc.validationQueryTimeout}")
    private String jdbcValidationQueryTimeout;

    @Value("${jdbc.testWhileIdle}")
    private String jdbcTestWhileIdle;

    @Value("${jdbc.testOnBorrow}")
    private String jdbcTestOnBorrow;

    @Value("${jdbc.testOnReturn}")
    private String jdbcTestOnReturn;

    @Value("${jdbc.preparedStatements}")
    private String jdbcPreparedStatements;

    @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}")
    private String jdbcMaxPoolPreparedStatementPerConnectionSize;

    @Value("${jdbc.filters}")
    private String jdbcFilters;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        try {
            DruidDataSource ds = new DruidDataSource();
            ds.setUrl(jdbcUrl);
            ds.setUsername(jdbcUsername);

            ResourceLoader resourceLoader = new DefaultResourceLoader();
            ds.setPassword(jdbcPassword);
            ds.setInitialSize(Integer.valueOf(jdbcInitialSize));
            ds.setMinIdle(Integer.valueOf(jdbcMinIdle));
            ds.setMaxActive(Integer.valueOf(jdbcMaxActive));
            ds.setMaxWait(Integer.valueOf(jdbcMaxWait));
            ds.setTimeBetweenEvictionRunsMillis(Integer.valueOf(jdbcTimeBetweenEvictionRunsMillis));
            ds.setMinEvictableIdleTimeMillis(Integer.valueOf(jdbcMinEvictableIdleTimeMillis));
            ds.setValidationQuery(jdbcValidationQuery);
            ds.setValidationQueryTimeout(Integer.valueOf(jdbcValidationQueryTimeout));
            ds.setTestWhileIdle(Boolean.valueOf(jdbcTestWhileIdle));
            ds.setTestOnBorrow(Boolean.valueOf(jdbcTestOnBorrow));
            ds.setTestOnReturn(Boolean.valueOf(jdbcTestOnReturn));
            ds.setPoolPreparedStatements(Boolean.valueOf(jdbcPreparedStatements));
            ds.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(jdbcMaxPoolPreparedStatementPerConnectionSize));
            ds.setFilters(jdbcFilters);

            return ds;
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory() {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        factory.setConfigLocation(resourceLoader.getResource("classpath:mybatis-config.xml"));
        try {
            return factory.getObject();
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }


//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }

//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager() {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//        transactionManager.setDataSource(dataSource());
//        return transactionManager;
//    }
}
