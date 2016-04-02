package com.omega.config

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import com.omega.debug.Debug.debug
import com.omega.debug.Debug.on
import com.omega.debug.Debug.off

trait BeanLifeCycle {
    
    @PostConstruct
    def postConstruct: Unit = {
        debug(on) {
            debug("Post Construct: " + this.getClass.getName)    
        }
    }
    
    @PreDestroy
    def preDestroy: Unit = {
        debug(off) {
            debug("Pre Destroy: " + this.getClass.getName)
        }
    }
}
