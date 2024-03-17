package com.neo.config;

import lombok.val;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.Duration;
import java.util.Date;


@Configuration
@ConfigurationProperties("oracle")
public class DataSourceConfig {
    @Autowired
    Environment environment;

    @Bean(name = "localDateSource")
    DataSource dataSource() throws Exception{
        String url = environment.getProperty("spring.datasource.url");
        String name = environment.getProperty("spring.datasource.name");
        String pw = environment.getProperty("spring.datasource.pw");
        String driver = environment.getProperty("spring.datasource.driver");

        int initSize = Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.initial-size"));
        int minIdle = Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.min_idle"));
        int maxIdle = Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.max_idle"));
        int maxTotal = Integer.parseInt(environment.getProperty("spring.datasource.dbcp2.max-total"));

        BasicDataSource dbcp2 = new BasicDataSource();
        dbcp2.setUrl(url);
        dbcp2.setUsername(name);
        dbcp2.setPassword(pw);
        dbcp2.setDriverClassName(driver);


        //设置连接池信息
        dbcp2.setInitialSize(initSize);
        dbcp2.setMinIdle(minIdle);
        dbcp2.setMaxIdle(maxIdle);
        dbcp2.setMaxTotal(maxTotal);

        //初始化的时候测试连接
        dbcp2.setTestOnBorrow(false);
        dbcp2.setTestWhileIdle(true);
        dbcp2.setMinEvictableIdleTimeMillis(6000);
        dbcp2.setTimeBetweenEvictionRunsMillis(6000);
        dbcp2.setValidationQuery("select 1 from dual");

//        dbcp2.setAutoCommitOnReturn(true);
//        dbcp2.setClearStatementPoolOnReturn(true);

        return dbcp2;
    }

    @Bean(name = "localJdbcTemplate")
    JdbcTemplate jdbcTemplate(@Qualifier("localDateSource")DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
}
