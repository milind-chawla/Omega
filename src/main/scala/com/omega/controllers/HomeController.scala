package com.omega.controllers

import java.util.Date
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import com.omega.service.BookService
import org.springframework.beans.factory.annotation.Autowired

@Controller
class HomeController {
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	model.addAttribute("date", new Date)
    	println(bookService.getBooks)
    	"index"
    }
}
