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

import com.omega.debug.Debug._

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.omega.service", "com.omega.repository"))
class OmegaCoreConfig extends ApplicationContextAware {
    
    //@Autowired
    //private var entityManagerFactory: EntityManagerFactory = _
    
    override def setApplicationContext(applicationContext: ApplicationContext): Unit = {
        OmegaCoreConfig.setApplicationContext(applicationContext)
    }
    
    @Bean
    def theDataSource: DataSource = {
        debug(on) {
            println("Constructing DataSource")
        }
        
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
        debug(on) {
            println("Constructing EntityManagerFactory")
        }
        
        val emfb: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        //emfb.setDataSource(this.dataSource)
        emfb.setDataSource(theDataSource)
        emfb.setJpaVendorAdapter(theJpaVendorAdapter)
        emfb.setPackagesToScan("com.omega.domain")
        emfb
    }
    
    @Bean
    def theTransactionManager: PlatformTransactionManager = {
        debug(on) {
            println("Constructing PlatformTransactionManager")    
        }
        
        val txManager: JpaTransactionManager = new JpaTransactionManager
        //txManager.setDataSource(this.dataSource)
        txManager.setDataSource(theDataSource)
        //txManager.setEntityManagerFactory(this.entityManagerFactory)
        txManager.setEntityManagerFactory(theEntityManagerFactory.getObject)
        txManager
    }
    
    @Bean
    def theJdbcTemplate: JdbcTemplate = {
        debug(on) {
            println("Constructing JdbcTemplate")
        }
        
        val jdbcTemplate: JdbcTemplate = new JdbcTemplate
        //jdbcTemplate.setDataSource(this.dataSource)
        jdbcTemplate.setDataSource(theDataSource)
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
