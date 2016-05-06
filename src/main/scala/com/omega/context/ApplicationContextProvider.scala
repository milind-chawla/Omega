package com.omega.context

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext
import com.omega.util.BeanLifeCycle

class ApplicationContextProvider extends ApplicationContextAware with BeanLifeCycle {
    
    @throws(classOf[BeansException])
    override def setApplicationContext(ac: ApplicationContext) {
        ApplicationContextProvider.setApplicationContext(ac)
    }
}

object ApplicationContextProvider {
    private var context: ApplicationContext = _
    
    @throws(classOf[BeansException])
    def setApplicationContext(ac: ApplicationContext) {
        context = ac
    }
    
    def getApplicationContext = context
}
