package com.phellipe.departaments.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection(){
        System.out.println("DB Connection from PROD - POSTGRES");
        System.out.println(driverClassName);
        System.out.println(url);

        return "DB Connection from PROD  - POSTGRES";
    }
}