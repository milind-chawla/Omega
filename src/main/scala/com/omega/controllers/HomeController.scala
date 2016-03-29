package com.omega.controllers

import java.util.Date

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomeController {
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	model.addAttribute("date", new Date)
    	"index"
    }
}
