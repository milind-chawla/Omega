package com.omega.util

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

trait BeanLifeCycle {
    
    @PostConstruct
    def postConstruct: Unit = {
        println("Post Construct: " + this.getClass.getName)
    }
    
    @PreDestroy
    def preDestroy: Unit = {
        println("Pre Destroy: " + this.getClass.getName)
    }
}
