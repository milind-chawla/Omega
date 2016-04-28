package com.omega.controllers

import scala.util.control.NonFatal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import com.omega.service.BookService
import com.omega.util.BeanLifeCycle

import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = Array("/home"))
class HomeController extends BeanLifeCycle {
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	// implicit def imp_gx(om: OM): Unit = imp_g(om)
        
        model {
            Map[Any, Any]() +
            ("date1" -> new java.util.Date) +
            ("date2" -> new java.util.Date) +
            ("date3" -> "new java.util.Date")
        }
    	
    	"home/index"
    }
}
