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
import javax.persistence.PersistenceUnit
import javax.sql.DataSource
import com.zaxxer.hikari.HikariDataSource

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement 
class OmegaCoreConfig extends BeanLifeCycle {
    
    /*@Bean
    def thePersistenceAnnotationBeanPostProcessor: PersistenceAnnotationBeanPostProcessor = new PersistenceAnnotationBeanPostProcessor*/
    
    /************************************************************USELESS BEANS**********************************************************/
    
    /*@PersistenceUnit
    private var entityManagerFactory: EntityManagerFactory = _*/
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @Autowired
    private var transactionManager: PlatformTransactionManager = _
    
    @Bean
    def thePersistenceExceptionTranslationPostProcessor: PersistenceExceptionTranslationPostProcessor = new PersistenceExceptionTranslationPostProcessor
    
    /*@Bean
    def theDataSource: DataSource = {
        debug.on("Start Constructing DataSource")
        
        val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
        builder.setType(EmbeddedDatabaseType.H2)
        builder.addScript("classpath:com/omega/database/schema.sql")
        builder.addScript("classpath:com/omega/database/data.sql")
        
        debug.on("End Constructing DataSource")
        
        builder.build
    }*/
    
    @Bean
    def theDataSource: DataSource = {
        debug.on("Start Constructing DataSource")
        
        val dataSource: HikariDataSource = new HikariDataSource
        val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
        
        builder.setType(EmbeddedDatabaseType.H2)
        builder.addScript("classpath:com/omega/database/schema.sql")
        builder.addScript("classpath:com/omega/database/data.sql")
        dataSource.setDataSource(builder.build)
        
        debug.on("End Constructing DataSource")
        
        dataSource
    }
    
    @Bean
    def theJpaVendorAdapter: AbstractJpaVendorAdapter = new HibernateJpaVendorAdapter
    
    @Bean
    def theEntityManagerFactory: FactoryBean[EntityManagerFactory] = {
        debug.on("Start Constructing FactoryBean[EntityManagerFactory]")
        
        val emfb: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        emfb.setPersistenceUnitName("OmegaUnit1")
        emfb.setDataSource(theDataSource)
        emfb.setJpaVendorAdapter(theJpaVendorAdapter)
        emfb.setPackagesToScan("com.omega.domain")
        
        debug.on("End Constructing FactoryBean[EntityManagerFactory]")
        
        emfb
    }
    
    @Bean(name = Array("OmegaTM1"))
    def theTransactionManager: PlatformTransactionManager = {
        debug.on("Start Constructing PlatformTransactionManager")
        
        val txManager: JpaTransactionManager = new JpaTransactionManager
        txManager.setEntityManagerFactory(theEntityManagerFactory.getObject)
        
        debug.on("End Constructing PlatformTransactionManager")
        
        txManager
    }
    
    @Bean
    def theJdbcTemplate: JdbcTemplate = {
        debug.on("Start Constructing JdbcTemplate")
        
        val jdbcTemplate: JdbcTemplate = new JdbcTemplate
        jdbcTemplate.setDataSource(theDataSource)
        
        debug.on("End Constructing JdbcTemplate")
        
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
