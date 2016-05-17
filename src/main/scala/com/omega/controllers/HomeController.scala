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
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping(value = Array("/home"))
class HomeController extends CController {
    import CControllerHelpers._
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(req: HttpServletRequest) = Action {
    	s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(req: HttpServletRequest) = Action(this) { mv =>
	    mv.setViewName(s"$lname/index")
	    mv
    }
}
