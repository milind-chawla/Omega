package com.omega.controllers

import com.omega.context.ApplicationContextProvider

object ControllerSpace {
    private var publicSpace = Map[Any, Any]()
    
    def insert(controllerClassName: String): Unit = {
        this.synchronized {
            publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Id" -> s"${controllerName(controllerClassName).toLowerCase}")
            publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Name" -> s"${controllerName(controllerClassName)}")
            publicSpace = publicSpace + (s"${controllerName(controllerClassName)}Path" -> s"${controllerPath(controllerClassName)}")
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