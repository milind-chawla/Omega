package com.omega.config

import org.springframework.beans.factory.FactoryBean
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

import com.omega.debug.Debug.debug
import com.omega.repository.BookDao
import com.omega.repository.BookDaoJpaImpl
import com.omega.service.BookService
import com.omega.service.BookServiceImpl
import com.omega.util.BeanLifeCycle
import com.zaxxer.hikari.HikariDataSource

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import com.omega.service.ActorService
import akka.actor.ActorSystem
import com.omega.service.ActorServiceImpl
import com.omega.context.ApplicationContextProvider

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement 
class OmegaCoreConfig extends BeanLifeCycle {
    /************************************************************USELESS BEANS**********************************************************/
    
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
    
    /*@Bean
    def theDataSource: DataSource = {
        debug.on("Start Constructing H2 DataSource")
        
        val dataSource: HikariDataSource = new HikariDataSource
        val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
        
        builder.setType(EmbeddedDatabaseType.H2)
        builder.addScript("classpath:com/omega/database/schema.sql")
        builder.addScript("classpath:com/omega/database/data.sql")
        dataSource.setDataSource(builder.build)
        
        debug.on("End Constructing H2 DataSource")
        
        dataSource
    }*/
    
    @Bean
    def theDataSource: DataSource = {
        debug.on("Start Constructing MySQL DataSource")
        
        val dataSource: HikariDataSource = new HikariDataSource
        
        dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource")
        dataSource.setUsername("user1")
        dataSource.setPassword("user1")
        dataSource.setAutoCommit(false)
        dataSource.setConnectionTimeout(5000)
        dataSource.setIdleTimeout(600000)
        dataSource.setMaxLifetime(1800000)
        dataSource.setConnectionTestQuery("SELECT 1")
        dataSource.setMaximumPoolSize(10)
        dataSource.setInitializationFailFast(false)
        //dataSource.setIsolateInternalQueries(false)
        //dataSource.setAllowPoolSuspension(false)
        //dataSource.setReadOnly(false)
        //dataSource.setRegisterMbeans(false)
        dataSource.setCatalog("user1")
        //dataSource.setConnectionInitSql(null)
        //dataSource.setDriverClassName("com.mysql.jdbc.Driver")
        dataSource.setValidationTimeout(10000)
        dataSource.setLeakDetectionThreshold(5000)
        
        debug.on("End Constructing MySQL DataSource")
        
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
    def theApplicationContextProvider = {
        debug.on("Constructing ApplicationContextProvider")
        
        new ApplicationContextProvider
    }
    
    @Bean
    def theActorService: ActorService = {
        debug.on("Constructing ActorService")
        
        new ActorServiceImpl(ActorSystem("OmegaActorSystem"))
    }
    
    @Bean
    def theBookDao: BookDao = {
        debug.on("Constructing BookDao")
        
        new BookDaoJpaImpl(theActorService)
    }
    
    @Bean
    def theBookService: BookService = {
        debug.on("Constructing BookService")
        
        new BookServiceImpl(theBookDao)
    }
}
