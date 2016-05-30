package com.omega.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView
import com.omega.util.BeanLifeCycle

@Configuration("OmegaWebApplicationConfig")
@EnableWebMvc
@ComponentScan(basePackages = Array("com.omega.controllers"))
class OmegaWebApplicationConfig extends WebMvcConfigurerAdapter with BeanLifeCycle {
    
    override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
        // registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926)
        registry
            .addResourceHandler("/resources/**")
            .addResourceLocations("/resources/")
            .setCachePeriod(31556926)
    }

    override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }
    
    @Bean
    def theJspViewResolver() = {
        val viewResolver = new InternalResourceViewResolver
        viewResolver.setViewClass(classOf[JstlView])
        viewResolver.setPrefix("/WEB-INF/views/jsp/")
        viewResolver.setSuffix(".jsp")
        viewResolver.setOrder(1)
        viewResolver
    }
    
    @Bean
    def theMultipartResolver() = {
        new org.springframework.web.multipart.support.StandardServletMultipartResolver
    }
}