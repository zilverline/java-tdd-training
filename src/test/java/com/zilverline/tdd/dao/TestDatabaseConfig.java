package com.zilverline.tdd.dao;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import com.zilverline.tdd.config.PersistenceConfig;

@Configuration
@ComponentScan(basePackages = { "com.zilverline.tdd.dao", "com.zilverline.tdd.domain" })
@Import({ PersistenceConfig.class })
public class TestDatabaseConfig {

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("/persistence.properties"));
        return ppc;
    }

}
