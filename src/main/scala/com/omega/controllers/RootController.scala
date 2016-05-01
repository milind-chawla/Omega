package com.omega.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import com.omega.util.BeanLifeCycle
import org.springframework.web.bind.annotation.RequestMethod
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping(value = Array("", "/"))
class RootController extends CController with BeanLifeCycle {
    
    @RequestMapping(method = Array(RequestMethod.GET))
	def root(implicit req: HttpServletRequest): String = {
    	"redirect:/home/index"
    }
}
