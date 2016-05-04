package com.omega.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import com.omega.util.BeanLifeCycle
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.ui.Model

@Controller
@RequestMapping(value = Array("/7minWorkout"))
class S7minWorkoutController extends CController with BeanLifeCycle {
    import com.omega.util.OmegaHelpers._
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest): String = {
        ""
    }
}
