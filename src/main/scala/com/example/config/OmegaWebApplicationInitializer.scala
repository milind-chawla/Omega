package com.example.config

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

class OmegaWebApplicationInitializer extends WebApplicationInitializer {
	
	@throws(classOf[ServletException])
	override def onStartup(servletContext: ServletContext): Unit = {
		registerDispatcherServlet(servletContext)
	}

	private def registerDispatcherServlet(servletContext: ServletContext): Unit = {
		val dispatcherContext: WebApplicationContext = createContext(classOf[OmegaWebApplicationConfig])
		val dispatcherServlet: DispatcherServlet = new DispatcherServlet(dispatcherContext)
		val dispatcher: ServletRegistration.Dynamic = servletContext.addServlet("dispatcher", dispatcherServlet)

		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
	}

	private def createContext(annotatedClasses: Class[_]): WebApplicationContext = {
		val context: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext()
		context.register(annotatedClasses)
		context
	}
}
