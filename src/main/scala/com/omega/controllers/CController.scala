package com.omega.controllers

import javax.servlet.http.HttpServletRequest

trait CController {
    def show: Boolean = true
    
    def uname: String = {
        val n = this.getClass.getCanonicalName.split("\\.").last
        val i = n.indexOf("Controller")
        
        n.substring(0, i)
    }
    
    def lname: String = {
        uname.toLowerCase
    }
    
    def contextPath(implicit req: HttpServletRequest): String = {
        req.getContextPath
    }
    
    def path(implicit req: HttpServletRequest): String = {
        s"$contextPath/$lname"
    }
    
    def path_new(implicit req: HttpServletRequest): String = {
        s"$contextPath/$lname/new"
    }
}