package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.ui.Model
import com.omega.util.JavaList

object CControllerHelpers {
    implicit class ModelAndViewMaker(mv: ModelAndView) {
        
        def apply(): ModelAndView = {
            mv.addObject("links", JavaList(ControllerSpace.getPublicSpace: _*))
        }
        
        def setForRedirect(): Unit = {
            mv.clear()
        }
    }
    
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
            
            mv.addObject("activate", s"${c.c_lname}") 
            mv.addObject("c_path", s"${c.c_path}") 
            mv.addObject("c_path_new", s"${c.c_path_new}")
            mv.addObject("c_uname", s"${c.c_uname}") 
            mv.addObject("c_lname", s"${c.c_lname}")
            mv.addObject("links", JavaList(ControllerSpace.getPublicSpace: _*))
            
            f(mv)
        }
    }
}