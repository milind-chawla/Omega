package com.omega.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.filter.CharacterEncodingFilter
import org.sitemesh.webapp.SiteMeshFilter
import org.sitemesh.config.ConfigurableSiteMeshFilter
import org.sitemesh.builder.SiteMeshFilterBuilder

@Configuration("OmegaWebApplicationConfig")
@EnableWebMvc
@Import(Array(classOf[OmegaCoreConfig], classOf[OmegaServiceConfig]))
@ComponentScan(basePackages = Array("com.omega.controllers"))
class OmegaWebApplicationConfig extends WebMvcConfigurerAdapter {
    
    override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926)
        registry.addResourceHandler("/stylesheets/**").addResourceLocations("/resources/stylesheets/").setCachePeriod(31556926)
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/").setCachePeriod(31556926)
        registry.addResourceHandler("/javascripts/**").addResourceLocations("/resources/javascripts/").setCachePeriod(31556926)
    }

    override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
    
    @Bean
    def theJspViewResolver(): InternalResourceViewResolver = {
        val viewResolver = new InternalResourceViewResolver
        viewResolver.setViewClass(classOf[JstlView])
        viewResolver.setPrefix("/WEB-INF/views/jsp/")
        viewResolver.setSuffix(".jsp")
        viewResolver.setOrder(1)
        viewResolver
    }
    
    @Bean
    def theCharacterEncodingFilter: CharacterEncodingFilter = {
        val characterEncodingFilter = new CharacterEncodingFilter
        characterEncodingFilter.setEncoding("UTF-8")
        characterEncodingFilter
    }
    
    @Bean
    def theSiteMeshFilter: ConfigurableSiteMeshFilter = {
        new ConfigurableSiteMeshFilter {
            override def applyCustomConfiguration(builder: SiteMeshFilterBuilder): Unit = {
                builder.addDecoratorPath("/*", "/WEB-INF/views/jsp/0/main.jsp")
            }
        }
    }
}