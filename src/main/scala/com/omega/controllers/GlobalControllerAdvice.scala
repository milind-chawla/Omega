package com.omega.controllers

import com.omega.util.BeanLifeCycle
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.ui.Model

@ControllerAdvice
class GlobalControllerAdvice extends BeanLifeCycle {
    import com.omega.util.OmegaHelpers._
    
    @ModelAttribute
    def globalAttributes(model: Model) {
    }
}
