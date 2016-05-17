package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import CControllerHelpers._
import javax.annotation.PostConstruct
import com.omega.util.BeanLifeCycle
import javax.annotation.PreDestroy
import com.omega.context.ApplicationContextProvider

abstract class CController extends BeanLifeCycle {
    
    @PostConstruct
    override def postConstruct: Unit = {
        println("Post Construct: " + this.getClass.getName)
        this.register
    }
    
    @PreDestroy
    override def preDestroy: Unit = {
        println("Pre Destroy: " + this.getClass.getName)
    }
    
    def show: Boolean = true
    
    def uname: String = {
        val n = this.getClass.getCanonicalName.split("\\.").last
        val i = n.indexOf("Controller")
        
        n.substring(0, i)
    }
    
    def lname: String = {
        uname.toLowerCase
    }
    
    def contextPath: String = {
        ApplicationContextProvider.getServletContext.getContextPath
    }
    
    def path: String = {
        s"$contextPath/$lname"
    }
    
    def path_new: String = {
        s"$contextPath/$lname/new"
    }
}