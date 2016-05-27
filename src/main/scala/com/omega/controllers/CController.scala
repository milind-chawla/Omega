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
    
    def c_uname: String = {
        val n = this.getClass.getCanonicalName.split("\\.").last
        val i = n.indexOf("Controller")
        
        n.substring(0, i)
    }
    
    def c_lname: String = {
        c_uname.toLowerCase
    }
    
    def contextPath: String = {
        ApplicationContextProvider.getServletContext.getContextPath
    }
    
    def c_path: String = {
        s"$contextPath/$c_lname"
    }
    
    def c_path_new: String = {
        s"$contextPath/$c_lname/new"
    }
}