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

@Configuration
@EnableWebMvc
@Import(Array(classOf[OmegaCoreConfig]))
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
}