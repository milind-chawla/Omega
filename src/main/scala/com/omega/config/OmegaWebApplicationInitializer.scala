package com.omega.config

import java.util.EnumSet
import org.sitemesh.builder.SiteMeshFilterBuilder
import org.sitemesh.config.ConfigurableSiteMeshFilter
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.request.RequestContextListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.DispatcherServlet
import com.omega.util.SiteMeshPathRegistration
import javax.servlet.DispatcherType
import javax.servlet.FilterRegistration
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.security.config.BeanIds

class OmegaWebApplicationInitializer extends WebApplicationInitializer {
    
    @throws(classOf[ServletException])
	override def onStartup(servletContext: ServletContext): Unit = {
        registerCharacterEncodingFilter(servletContext)
        
        registerContextLoaderListener(servletContext)
        registerDispatcherServlet(servletContext)
        registerSpringSecurityFilterChain(servletContext)
		registerSiteMeshFilter(servletContext)
	}
    
    private def registerSpringSecurityFilterChain(servletContext: ServletContext): Unit = {
        val filter: FilterRegistration.Dynamic = servletContext.addFilter(BeanIds.SPRING_SECURITY_FILTER_CHAIN, new DelegatingFilterProxy)
	    filter.addMappingForUrlPatterns(null, false, "/*")
    }
    
    private def registerContextLoaderListener(servletContext: ServletContext): Unit = {
        val rootContext: AnnotationConfigWebApplicationContext = createOmegaCoreConfigContext
		servletContext.addListener(new ContextLoaderListener(rootContext))
		servletContext.addListener(new RequestContextListener())
	}

	private def registerDispatcherServlet(servletContext: ServletContext): Unit = {
	    val rootContext: AnnotationConfigWebApplicationContext = createOmegaWebApplicationConfigContext
		val dispatcherServlet: DispatcherServlet = new DispatcherServlet(rootContext)
		val dispatcher: ServletRegistration.Dynamic = servletContext.addServlet("dispatcher", dispatcherServlet)

		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
	}
	
	private def registerCharacterEncodingFilter(servletContext: ServletContext): Unit = {
	    val characterEncodingFilter = new CharacterEncodingFilter
        characterEncodingFilter.setEncoding("UTF-8")
        characterEncodingFilter.setForceEncoding(true)
        
	    val filter: FilterRegistration.Dynamic = servletContext.addFilter("characterEncoding", characterEncodingFilter)
	    filter.addMappingForUrlPatterns(null, false, "/*")
	}
	
	private def registerSiteMeshFilter(servletContext: ServletContext): Unit = {
	    val filter: FilterRegistration.Dynamic = servletContext.addFilter("sitemesh", new ConfigurableSiteMeshFilter {
	        override def applyCustomConfiguration(builder: SiteMeshFilterBuilder): Unit = {
	            SiteMeshPathRegistration(builder)
	        }
	    })
	    
	    filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*")
	}
	
	private def createOmegaWebApplicationConfigContext: AnnotationConfigWebApplicationContext = {
		val context: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext()
		context.register(classOf[OmegaWebApplicationConfig])
		context
	}
	
	private def createOmegaCoreConfigContext: AnnotationConfigWebApplicationContext = {
		val context: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext()
		context.register(classOf[OmegaCoreConfig], classOf[OmegaSecurityConfig])
		context
	}
}