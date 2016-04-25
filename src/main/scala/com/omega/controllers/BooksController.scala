package com.omega.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod
import com.omega.util.OmegaHelpers._

@Controller
@RequestMapping(value = Array("/books"))
class BooksController {
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def home(model: Model): String = {
    	null
    }
}