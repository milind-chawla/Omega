package com.omega.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView

@Configuration("OmegaWebApplicationConfig")
@EnableWebMvc
@Import(Array(classOf[OmegaCoreConfig], classOf[OmegaServiceConfig]))
@ComponentScan(basePackages = Array("com.omega.controllers"))
class OmegaWebApplicationConfig extends WebMvcConfigurerAdapter {
    
    override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
        // registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926)
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926)
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
}