package com.omega.controllers.angular

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import com.omega.controllers.CController
import com.omega.controllers.CControllerHelpers
import com.omega.controllers.CControllerHelpers.Action

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = Array("/angularseed"))
class AngularSeedController extends CController {
    import CControllerHelpers._
    
    override def config = configs.angularseed
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(req: HttpServletRequest) = Action {
    	s"redirect:$rpath/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(req: HttpServletRequest) = Action(this) { mv =>
    	mv.setViewName(view("index"))
    	mv
    }
}