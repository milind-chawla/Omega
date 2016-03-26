package com.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView

// import org.thymeleaf.spring4.SpringTemplateEngine
// import org.thymeleaf.spring4.view.ThymeleafViewResolver
// import org.thymeleaf.templateresolver.ServletContextTemplateResolver

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = Array("com.example.controllers"))
class OmegaWebApplicationConfig extends WebMvcConfigurerAdapter {

    override def addResourceHandlers(registry: ResourceHandlerRegistry): Unit = {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926)
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926)
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926)
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926)
    }

    override def configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

    // To Be Implemented Later
    // @Bean
    // def getThymeleafServletContextTemplateResolver(): ServletContextTemplateResolver = {
    //     val viewResolver = new ServletContextTemplateResolver
    //     viewResolver.setPrefix("/WEB-INF/views/html/")
    //     viewResolver.setSuffix(".html")
    //     viewResolver.setTemplateMode("HTML5")
    //     viewResolver.setOrder(1)
    //     viewResolver
    // }

    // @Bean
    // def getThymeleafSpringTemplateEngine(): SpringTemplateEngine = {
    // 	val engine: SpringTemplateEngine = new SpringTemplateEngine
    // 	engine.setTemplateResolver(getThymeleafServletContextTemplateResolver())
    // 	engine
    // }

    // @Bean
    // def getThymeleafViewResolver(): ThymeleafViewResolver = {
    // 	val viewResolver = new ThymeleafViewResolver
    // 	viewResolver.setTemplateEngine(getThymeleafSpringTemplateEngine())
    // 	viewResolver.setViewNames(Array("*.html", "*.xhtml"))
    // 	viewResolver
    // }

    @Bean
    def getJspViewResolver(): InternalResourceViewResolver = {
        val viewResolver = new InternalResourceViewResolver
        viewResolver.setViewClass(classOf[JstlView])
        viewResolver.setPrefix("/WEB-INF/views/")
        viewResolver.setSuffix(".jsp")
        // viewResolver.setOrder(2)
        viewResolver
    }
}
