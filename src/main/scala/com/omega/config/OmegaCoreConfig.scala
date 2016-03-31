package com.omega.config

import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.omega.service", "com.omega.repository"))
class OmegaCoreConfig extends ApplicationContextAware {
    
    @Autowired
    private var dataSource: DataSource = _
    
    @Autowired
    private var entityManagerFactory: EntityManagerFactory = _
    
    override def setApplicationContext(applicationContext: ApplicationContext): Unit = {
        OmegaCoreConfig.setApplicationContext(applicationContext)
    }
    
    @Bean
    def theDataSource: DataSource = {
        val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
        builder.setType(EmbeddedDatabaseType.H2)
        builder.addScript("classpath:com/omega/database/schema.sql")
        builder.addScript("classpath:com/omega/database/data.sql")
        builder.build
    }
    
    @Bean
    def theJpaVendorAdapter: AbstractJpaVendorAdapter = new HibernateJpaVendorAdapter
    
    @Bean
    def theEntityManagerFactory: FactoryBean[EntityManagerFactory] = {
        val emfb: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        emfb.setDataSource(this.dataSource)
        emfb.setJpaVendorAdapter(theJpaVendorAdapter)
        emfb.setPackagesToScan("com.omega.domain")
        emfb
    }
    
    @Bean
    def theTransactionManager: PlatformTransactionManager = {
        val txManager: JpaTransactionManager = new JpaTransactionManager
        txManager.setDataSource(this.dataSource)
        txManager.setEntityManagerFactory(this.entityManagerFactory)
        txManager
    }
    
    @Bean
    def theJdbcTemplate: JdbcTemplate = {
        val jdbcTemplate: JdbcTemplate = new JdbcTemplate
        jdbcTemplate.setDataSource(this.dataSource)
        jdbcTemplate
    }
}

object OmegaCoreConfig {
    private var applicationContext: ApplicationContext = _
    
    def setApplicationContext(applicationContext: ApplicationContext): Unit = {
        this.applicationContext = applicationContext
    }
    
    def getApplicationContext: ApplicationContext = applicationContext 
}
