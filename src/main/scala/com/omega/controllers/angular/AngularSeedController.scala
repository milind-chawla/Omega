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
    
    this.register
    
    override def show: Boolean = true
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest) = {
    	s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model)(implicit req: HttpServletRequest) = {
    	// implicit def imp_gx(om: OM): Unit = imp_g(om)
        model(this)
        
    	s"$lname/index"
    }
}