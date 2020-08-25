package com.phellipe.departaments.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection(){
        System.out.println("DB Connection from DEV - H2");
        System.out.println(driverClassName);
        System.out.println(url);

        return "DB Connection from DEV  - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection(){
        System.out.println("DB Connection from TEST - H2");
        System.out.println(driverClassName);
        System.out.println(url);

        return "DB Connection from TEST  - H2";
    }

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Profile("prod")
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
    @Profile("prod")
    @Bean
    public String prodDatabaseConnection(){
        System.out.println("DB Connection from PROD - POSTGRES");
        System.out.println(driverClassName);
        System.out.println(url);

        return "DB Connection from PROD  - POSTGRES";
    }

}