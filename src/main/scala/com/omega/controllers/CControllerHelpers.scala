package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.ui.Model
import com.omega.util.JavaList

object CControllerHelpers {
    object configs {
        def root = ControllerConfig.RootControllerConfig
        def home = ControllerConfig.HomeControllerConfig
        def books = ControllerConfig.BooksControllerConfig
        def angularseed = ControllerConfig.AngularSeedControllerConfig
    }
    
    object Action {
        def apply(f: => String): String = {
            f
        }
        
        def apply(c: CController)(f: ModelAndView => ModelAndView): ModelAndView = {
            val mv = new ModelAndView
            
            mv.addObject("id", s"${c.config.id}")
            mv.addObject("name", s"${c.config.name}")
            mv.addObject("apath", s"${c.config.apath}") 
            mv.addObject("links", ControllerConfig.controllerLinkage)
            
            f(mv)
        }
    }
}