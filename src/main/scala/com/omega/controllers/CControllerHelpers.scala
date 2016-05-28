package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.ui.Model
import com.omega.util.JavaList

object CControllerHelpers {
    implicit class ControllerRegistrationHelper[C <: CController](c: C) {
        
        def register: Unit = {
            ControllerSpace.insert(c)
        }
    }
    
    object Action {
        def apply(f: => String): String = {
            f
        }
        
        def apply(c: CController)(f: ModelAndView => ModelAndView): ModelAndView = {
            val mv = new ModelAndView
            
            mv.addObject("activate", s"${c.lname}") 
            mv.addObject("path", s"${c.path}") 
            mv.addObject("path_new", s"${c.path_new}")
            mv.addObject("uname", s"${c.uname}") 
            mv.addObject("lname", s"${c.lname}")
            mv.addObject("links", JavaList(ControllerSpace.getPublicSpace: _*))
            
            f(mv)
        }
    }
}