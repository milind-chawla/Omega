package com.example.config

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType

import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.example.service", "com.example.repository"))
class OmegaInfrastructureContextConfiguration {

	@Autowired
	private var dataSource: DataSource = _

	@Autowired
	private var entityManagerFactory: EntityManagerFactory = _

	@Bean
	def getEntityManagerFactory: FactoryBean[EntityManagerFactory] = {
		val emf: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
		emf.setPackagesToScan("com.myexample.domain");
		emf.setPersistenceUnitName("OmegaUnit");
		// emf.setPersistenceProviderClass(classOf[HibernatePersistenceProvider]);
		emf.setDataSource(dataSource)
		emf.setJpaVendorAdapter(getJpaVendorAdapter)
		// emf.setLoadtimeWeaver(classOf[InstrumentationLoadTimeWeaver])
		emf.afterPropertiesSet()
		emf
	}

	@Bean
	def getJpaVendorAdapter: JpaVendorAdapter = {
		new HibernateJpaVendorAdapter
	}

	@Bean
	def getTransactionManager: PlatformTransactionManager = {
		val txManager: JpaTransactionManager = new JpaTransactionManager
		txManager.setEntityManagerFactory(entityManagerFactory)
		txManager.setDataSource(dataSource)
		txManager
	}

	@Bean
	def getDataSource: DataSource = {
		val builder: EmbeddedDatabaseBuilder = new EmbeddedDatabaseBuilder
		builder.setType(EmbeddedDatabaseType.H2)
		builder.addScript("classpath:com/example/database/schema.sql")
		builder.addScript("classpath:com/example/database/data.sql")
		builder.build
	}
}
