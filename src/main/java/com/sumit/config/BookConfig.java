package com.sumit.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "bookEntityManagerFactory",
        basePackages = {
                "com.sumit.db2.repositories"
        }
)
public class BookConfig {

    // datasource configuration
    @Primary
    @Bean(name = "bookDataSource")
    @ConfigurationProperties(prefix = "spring.db2.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder
                .create()
                .build();
    }


    //entityManagerFactory
    @Primary
    @Bean(name = "bookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("bookDataSource") DataSource dataSource){
        HashMap<String, Object> properties=new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto","update");

        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.sumit.db2.entities")
                .persistenceUnit("db2")
                .build();
    }

    @Primary
    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("bookEntityManagerFactory")EntityManagerFactory bookEntityManagerFactory){
        return  new JpaTransactionManager(bookEntityManagerFactory);
    }
}
