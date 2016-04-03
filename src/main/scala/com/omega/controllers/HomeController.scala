package com.omega.controllers

import org.springframework.stereotype.Controller
import com.omega.service.BookService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMethod
import java.util.Date
import com.omega.util.BeanLifeCycle

@Controller
class HomeController extends BeanLifeCycle {
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def home(model: Model) = {
    	"redirect:/home/index"
    }
    
    @RequestMapping(value = Array("/home/index"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	model.addAttribute("date", new Date)
    	model.addAttribute("books", bookService.getBooks)
    	"home/index"
    }
}
