package com.omega.config

import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired
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
import com.omega.debug.Debug.debug
import com.omega.debug.Debug.on
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import com.omega.util.BeanLifeCycle

@Configuration("OmegaCoreConfig")
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.omega.repository"))
class OmegaCoreConfig extends BeanLifeCycle {
    
    @Autowired
    private var entityManagerFactory: EntityManagerFactory = _
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @Autowired
    private var transactionManager: PlatformTransactionManager = _
    
    @Bean
    def theDataSource: DataSource = {
        val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
        builder.setType(EmbeddedDatabaseType.H2)
        builder.addScript("classpath:com/omega/database/schema.sql")
        builder.addScript("classpath:com/omega/database/data.sql")
        
        val dataSource = builder.build
        
        debug(on) {
            debug("Constructing DataSource: " + dataSource)
        }
        
        dataSource
    }
    
    @Bean
    def theJpaVendorAdapter: AbstractJpaVendorAdapter = new HibernateJpaVendorAdapter
    
    @Bean
    def theEntityManagerFactory: FactoryBean[EntityManagerFactory] = {
        val emfb: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        emfb.setDataSource(theDataSource)
        emfb.setJpaVendorAdapter(theJpaVendorAdapter)
        emfb.setPackagesToScan("com.omega.domain")
        
        debug(on) {
            debug("Constructing FactoryBean[EntityManagerFactory]: " + emfb)
        }
        
        emfb
    }
    
    @Bean
    def theTransactionManager: PlatformTransactionManager = {
        val txManager: JpaTransactionManager = new JpaTransactionManager
        txManager.setDataSource(theDataSource)
        txManager.setEntityManagerFactory(entityManagerFactory)
        
        debug(on) {
            debug("Constructing PlatformTransactionManager: " + txManager + ", " + entityManagerFactory)    
        }
        
        txManager
    }
    
    @Bean
    def theJdbcTemplate: JdbcTemplate = {
        val jdbcTemplate: JdbcTemplate = new JdbcTemplate
        jdbcTemplate.setDataSource(theDataSource)
        
        debug(on) {
            debug("Constructing JdbcTemplate: " + jdbcTemplate)
        }
        
        jdbcTemplate
    }
}
