package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.ui.Model

object CControllerHelpers {
    implicit class ModelMaker(model: Model) {
        
        def apply(): Model = {
            model {
                ControllerSpace.getPublicSpace
            }
        }
        
        def apply(m: => Map[_, _]): Model = {
            m.map { case(k, v) =>
                model.addAttribute(k.toString, v)
            }
            
            model
        }
        
        def apply[C <: CController](c: C)(implicit req: HttpServletRequest): Model = {
            model()
            
            model {
                Map[Any, Any]() + 
                ("activate" -> s"${c.lname}") + 
                ("path" -> s"${c.path}") + 
                ("path_new" -> s"${c.path_new}") +
                ("uname" -> s"${c.uname}") + 
                ("lname" -> s"${c.lname}")
            }
        }
    }
    
    implicit class ModelAndViewMaker(mav: ModelAndView) {
        
        def apply(): ModelAndView = {
            mav {
                ControllerSpace.getPublicSpace
            }
        }
        
        def apply(m: => Map[_, _]): ModelAndView = {
            m.map { case(k, v) =>
                mav.addObject(k.toString, v)
            }
            
            mav
        }
        
        def apply[C <: CController](c: C)(implicit req: HttpServletRequest): ModelAndView = {
            mav()
            
            mav {
                Map[Any, Any]() + 
                ("activate" -> s"${c.lname}") + 
                ("path" -> s"${c.path}") + 
                ("path_new" -> s"${c.path_new}") +
                ("uname" -> s"${c.uname}") + 
                ("lname" -> s"${c.lname}")
            }
        }
    }
    
    implicit class ControllerRegistrationHelper[C <: CController](c: C) {
        
        def register: Unit = {
            ControllerSpace.insert(c.getClass.getCanonicalName)
        }
    }
}