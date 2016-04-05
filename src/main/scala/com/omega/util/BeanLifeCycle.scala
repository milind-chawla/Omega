package com.omega.util

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import com.omega.debug.Debug.debug

trait BeanLifeCycle {
    
    @PostConstruct
    def postConstruct: Unit = {
        debug.on("Post Construct: " + this.getClass.getName)
    }
    
    @PreDestroy
    def preDestroy: Unit = {
        debug.off("Pre Destroy: " + this.getClass.getName)
    }
}
