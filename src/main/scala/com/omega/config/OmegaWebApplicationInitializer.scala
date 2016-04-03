package com.omega.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration
import org.springframework.web.context.request.RequestContextListener
import org.sitemesh.config.ConfigurableSiteMeshFilter
import javax.servlet.FilterRegistration
import java.util.EnumSet
import javax.servlet.DispatcherType
import org.sitemesh.builder.SiteMeshFilterBuilder

class OmegaWebApplicationInitializer extends WebApplicationInitializer {
    
    private var rootContext: AnnotationConfigWebApplicationContext = _
    
    @throws(classOf[ServletException])
	override def onStartup(servletContext: ServletContext): Unit = {
        initialize(servletContext)
        
        registerListener(servletContext)
		registerDispatcherServlet(servletContext)
		registerSiteMeshFilter(servletContext)
	}
    
    private def initialize(servletContext: ServletContext): Unit = {
        this.rootContext = createContext(classOf[OmegaWebApplicationConfig])
    }
    
    private def registerListener(servletContext: ServletContext): Unit = {
		servletContext.addListener(new ContextLoaderListener(rootContext))
        servletContext.addListener(new RequestContextListener())
	}

	private def registerDispatcherServlet(servletContext: ServletContext): Unit = {
		val dispatcherServlet: DispatcherServlet = new DispatcherServlet(rootContext)
		val dispatcher: ServletRegistration.Dynamic = servletContext.addServlet("dispatcher", dispatcherServlet)

		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
	}
	
	private def registerSiteMeshFilter(servletContext: ServletContext): Unit = {
	    val filter: FilterRegistration.Dynamic = servletContext.addFilter("sitemesh", new ConfigurableSiteMeshFilter {
	        override def applyCustomConfiguration(builder: SiteMeshFilterBuilder): Unit = {
	            builder.addDecoratorPath("/home/*", "/WEB-INF/views/jsp/_layout0/home.jsp")
	        }
	    })
	    
	    filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), true, "/*")
	}

	private def createContext(annotatedClasses: Class[_]): AnnotationConfigWebApplicationContext = {
		val context: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext()
		context.register(annotatedClasses)
		context
	}
}