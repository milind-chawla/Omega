package com.omega.context

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import com.omega.util.BeanLifeCycle
import org.springframework.web.context.ServletContextAware
import javax.servlet.ServletContext

class ApplicationContextProvider extends ApplicationContextAware with ServletContextAware with BeanLifeCycle {
    
    @throws(classOf[BeansException])
    override def setApplicationContext(applicationContext: ApplicationContext) {
        ApplicationContextProvider.setApplicationContext(applicationContext)
    }
    
    @throws(classOf[BeansException])
    override def setServletContext(servletContext: ServletContext) {
        ApplicationContextProvider.setServletContext(servletContext)
    }
}

object ApplicationContextProvider {
    private var applicationContext: ApplicationContext = _
    private var servletContext: ServletContext = _
    
    @throws(classOf[BeansException])
    def setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }
    
    @throws(classOf[BeansException])
    def setServletContext(servletContext: ServletContext) {
        this.servletContext = servletContext
    }
    
    def getApplicationContext = applicationContext
    def getServletContext = servletContext
}
