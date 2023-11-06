package com.pichincha.accounts.infrastructure.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

@Lazy
@Configuration
public class DataSourceConfig {

    @Value("${datasource.url}")
    private String url;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.maximumPoolSize}")
    private int maximumPoolSize;

    @Bean
    public DataSource getDataSource() {
        var config = new HikariConfig();

        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);

        return new HikariDataSource(config);
    }
}
