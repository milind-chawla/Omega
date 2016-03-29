package com.omega.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

class OmegaWebApplicationInitializer extends WebApplicationInitializer {
    
    @throws(classOf[ServletException])
	override def onStartup(servletContext: ServletContext): Unit = {
		registerDispatcherServlet(servletContext)
	}

	private def registerDispatcherServlet(servletContext: ServletContext): Unit = {
		val rootContext: AnnotationConfigWebApplicationContext = createContext(Array(classOf[OmegaWebApplicationConfig]))
		servletContext.addListener(new ContextLoaderListener(rootContext))
		
		val dispatcherServlet: DispatcherServlet = new DispatcherServlet(rootContext)
		val dispatcher: ServletRegistration.Dynamic = servletContext.addServlet("dispatcher", dispatcherServlet)

		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
	}

	private def createContext(annotatedClasses: Array[Class[_]]): AnnotationConfigWebApplicationContext = {
		val context: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext()
		
		for(clazz <- annotatedClasses)
		    context.register(clazz)
		    
		context
	}
}