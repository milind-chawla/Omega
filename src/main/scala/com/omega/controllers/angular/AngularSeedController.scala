package com.omega.controllers.angular

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import com.omega.util.BeanLifeCycle
import javax.servlet.http.HttpServletRequest
import org.springframework.ui.Model
import com.omega.controllers.CController
import com.omega.controllers.CControllerHelpers
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = Array("/angularseed"))
class AngularSeedController extends CController with BeanLifeCycle {
    import CControllerHelpers._
    import com.omega.util.OmegaHelpers._
    
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