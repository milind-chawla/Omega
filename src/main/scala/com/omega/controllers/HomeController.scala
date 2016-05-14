package com.omega.controllers

import scala.util.control.NonFatal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import com.omega.service.BookService
import com.omega.util.BeanLifeCycle

import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(value = Array("/home"))
class HomeController extends CController with BeanLifeCycle {
    import CControllerHelpers._
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    this.register
    
    override def show: Boolean = true
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest) = {
    	s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model)(implicit req: HttpServletRequest) = {
    	// implicit def imp_gx(om: OM): Unit = imp_g(om)
        model(this)
        
    	s"$lname/index"
    }
}
