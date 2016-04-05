package com.omega.config

import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.Transactional

import com.omega.debug.Debug.debug
import com.omega.repository.BookDao
import com.omega.repository.BookDaoJpaImpl
import com.omega.service.BookService
import com.omega.service.BookServiceImpl
import com.omega.util.BeanLifeCycle

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement 
class OmegaCoreConfig extends BeanLifeCycle {
    
    /*@Bean
    def thePersistenceAnnotationBeanPostProcessor: PersistenceAnnotationBeanPostProcessor = new PersistenceAnnotationBeanPostProcessor*/
    
    /************************************************************USELESS BEANS**********************************************************/
    
    @Autowired
    private var entityManagerFactory: EntityManagerFactory = _
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @Autowired
    private var transactionManager: PlatformTransactionManager = _
    
    @Bean
    def thePersistenceExceptionTranslationPostProcessor: PersistenceExceptionTranslationPostProcessor = new PersistenceExceptionTranslationPostProcessor
    
    @Bean
    def theDataSource: DataSource = {
        debug.on("Constructing DataSource")
        
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
        debug.on("Constructing FactoryBean[EntityManagerFactory]")
        
        val emfb: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        emfb.setDataSource(theDataSource)
        emfb.setJpaVendorAdapter(theJpaVendorAdapter)
        emfb.setPackagesToScan("com.omega.domain")
        
        emfb
    }
    
    @Bean
    def theTransactionManager: PlatformTransactionManager = {
        debug.on("Constructing PlatformTransactionManager")
        
        val txManager: JpaTransactionManager = new JpaTransactionManager
        txManager.setEntityManagerFactory(entityManagerFactory)
        
        txManager
    }
    
    @Bean
    def theJdbcTemplate: JdbcTemplate = {
        debug.on("Constructing JdbcTemplate")
        
        val jdbcTemplate: JdbcTemplate = new JdbcTemplate
        jdbcTemplate.setDataSource(theDataSource)
        
        jdbcTemplate
    }
    
    /************************************************************USEFULL BEANS**********************************************************/
    
    @Bean
    def theBookDao: BookDao = {
        debug.on("Constructing BookDao")
        
        new BookDaoJpaImpl
    }
    
    @Bean
    def theBookService: BookService = {
        debug.on("Constructing BookService")
        
        new BookServiceImpl(theBookDao)
    }
}
