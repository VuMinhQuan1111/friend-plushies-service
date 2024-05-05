package com.friendsplushies.config;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author vu@investidea.tech
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.friendsplushies.repository")
@EnableTransactionManagement
public class DatabaseConfig {

  @Autowired
  private Environment environment;

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "datasource.properties")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  public DataSource dataSource() {
    DataSourceProperties dataSourceProperties = dataSourceProperties();
    return DataSourceBuilder
        .create(dataSourceProperties.getClassLoader())
        .driverClassName(dataSourceProperties.getDriverClassName())
        .url(dataSourceProperties.getUrl())
        .username(dataSourceProperties.getUsername())
        .password(dataSourceProperties.getPassword())
        .type(HikariDataSource.class)
        .build();
  }

  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setPackagesToScan(new String[]{environment.getRequiredProperty("datasource.properties.scanPackage")});
    factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
    factoryBean.setJpaProperties(jpaProperties());
    return factoryBean;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean
  @Autowired
  @Primary
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(emf);
    return txManager;
  }

  private Properties jpaProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", environment.getRequiredProperty("datasource.hibernate.dialect"));
    properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.hibernate.hbm2ddl.method"));
    properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.hibernate.show_sql"));
    properties.put("hibernate.format_sql", environment.getRequiredProperty("datasource.hibernate.format_sql"));
    properties.put("hibernate.enable_lazy_load_no_trans", environment.getRequiredProperty("datasource.hibernate.enable_lazy_load_no_trans"));
    properties.put("hibernate.temp.use_jdbc_metadata_defaults", environment.getRequiredProperty("datasource.hibernate.use_jdbc_metadata_defaults"));
    properties.put("hibernate.default_schema", environment.getRequiredProperty("datasource.hibernate.default_schema"));
    properties.put("hibernate.jdbc.batch_size", String.valueOf(50));
    properties.put("spring.jpa.properties.javax.persistence.validation.mode", "none");
    return properties;
  }
}
