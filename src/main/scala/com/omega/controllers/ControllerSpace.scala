package com.omega.controllers

import com.omega.context.ApplicationContextProvider

object ControllerSpace {
    private[this] var publicSpace = Map[Any, Any]()
    
    def insert[C <: CController](c: C): Unit = {
        if(c.show) {
            this.synchronized {
                val controllerClassName: String = c.getClass.getCanonicalName
                
                publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Id" -> s"${controllerName(controllerClassName).toLowerCase}")
                publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Name" -> s"${controllerName(controllerClassName)}")
                publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Path" -> s"${controllerPath(controllerClassName)}")
            }
        }
    }
    
    def getPublicSpace = (publicSpace + ("" -> ""))
    
    private def controllerName(controllerClassName: String): String = {
        val n = controllerClassName.split("\\.").last
        val i = n.indexOf("Controller")
        n.substring(0, i)
    }
    
    private def controllerPath(controllerClassName: String): String = {
        val n = controllerName(controllerClassName).toLowerCase
        s"${ApplicationContextProvider.getServletContext.getContextPath}/${n}"
    }
}