package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.core.annotation.AnnotationUtils

class GlobalDefaultExceptionHandler {
    val DEFAULT_ERROR_VIEW = "error";
    
    @throws(classOf[Exception])
    @ExceptionHandler(value = Array(classOf[Exception]))
    def defaultErrorHandler(req: HttpServletRequest, e: Exception): ModelAndView = {
        if(AnnotationUtils.findAnnotation(e.getClass(), classOf[ResponseStatus]) != null) throw e
        
        val mav = new ModelAndView
        
        mav.addObject("exception", e)
        mav.addObject("url", req.getRequestURL())
        mav.setViewName(DEFAULT_ERROR_VIEW)
        
        mav
    }
}