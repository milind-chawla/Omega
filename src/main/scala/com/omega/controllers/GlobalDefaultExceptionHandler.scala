package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.core.annotation.AnnotationUtils
import com.omega.util.BeanLifeCycle
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ResponseBody
import com.omega.exceptions.JSONException
import com.omega.util.JavaMap
import com.omega.exceptions.BookNotFoundException
import com.omega.util.JavaList

@ControllerAdvice
class GlobalDefaultExceptionHandler extends BeanLifeCycle {
    import CControllerHelpers._
    import com.omega.util.OmegaHelpers._
    
    @throws(classOf[Exception])
    @ExceptionHandler(value = Array(classOf[Exception]))
    def exception(req: HttpServletRequest, e: Exception): ModelAndView = {
        if(AnnotationUtils.findAnnotation(e.getClass(), classOf[ResponseStatus]) != null) throw e
        
        val mv = new ModelAndView
        
        mv.addObject("links", ControllerConfig.controllerLinkage)
        mv.addAllObjects(JavaMap("error" -> e, "url" -> req.getRequestURL(), "ste" -> e.getStackTrace))
        mv.setViewName("_common0/exp")
        
        mv
    }
    
    @throws(classOf[Exception])
    @ExceptionHandler(value = Array(classOf[BookNotFoundException]))
    def bookNotFoundException(req: HttpServletRequest, e: BookNotFoundException): ModelAndView = {
        if(AnnotationUtils.findAnnotation(e.getClass(), classOf[ResponseStatus]) != null) throw e
        
        val mv = new ModelAndView
        
        mv.addObject("links", ControllerConfig.controllerLinkage)
        mv.addAllObjects(JavaMap("id" -> e.bookId))
        mv.setViewName("books/404")
        
        mv
    }
    
    @throws(classOf[Exception])
    @ExceptionHandler(value = Array(classOf[JSONException]))
    @ResponseBody
    def jSONException(req: HttpServletRequest, e: JSONException): java.util.Map[String, _] = {
        if(AnnotationUtils.findAnnotation(e.getClass(), classOf[ResponseStatus]) != null) throw e
        
        JavaMap("error" -> e.getMessage, "url" -> req.getRequestURL(), "ste" -> e.getStackTrace)
    }
}