package com.omega.controllers

import com.omega.context.ApplicationContextProvider

object ControllerSpace {
    private[this] var publicSpace = List[(String, String, String)]()
    
    def insert[C <: CController](c: C): Unit = {
        if(c.show) {
            this.synchronized {
                val controllerClassName: String = c.getClass.getCanonicalName
                
                val id = controllerName(controllerClassName).toLowerCase
                val name = controllerName(controllerClassName)
                val path = controllerPath(controllerClassName)
                
                publicSpace = publicSpace :+ (s"${id}", s"${name}", s"${path}")
            }
        }
    }
    
    def getPublicSpace: List[(String, String, String)] = {
        val root = publicSpace.find(_._1 == "root").get
        val home = publicSpace.find(_._1 == "home").get
        
        val subspace = publicSpace.filter(x => x._1 != "root" && x._1 != "home").sortBy(_._1)
        home +: subspace
    }
    
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