package com.omega.controllers

import org.springframework.stereotype.Controller
import com.omega.service.BookService
import org.springframework.web.bind.annotation.RequestMapping
import com.omega.config.BeanLifeCycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod
import java.util.Date

@Controller
class HomeController extends BeanLifeCycle {
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	model.addAttribute("date", new Date)
    	println(bookService.getBooks)
    	"index"
    }
}
