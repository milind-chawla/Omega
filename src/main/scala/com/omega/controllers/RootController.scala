package com.omega.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = Array("/"))
class RootController {
    
    @RequestMapping(method = Array(RequestMethod.GET))
	def root: String = {
    	"redirect:/home/index"
    }
}