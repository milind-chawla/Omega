package com.omega.controllers

import javax.servlet.http.HttpServletRequest
import CControllerHelpers._
import javax.annotation.PostConstruct
import com.omega.util.BeanLifeCycle
import javax.annotation.PreDestroy
import com.omega.context.ApplicationContextProvider

abstract class CController extends BeanLifeCycle {
    
    def config: ControllerConfig = ???
    
    def id: String = config.id
    def name: String = config.name
    def view(v: String): String = config.view(v)
    def apath: String = config.apath
    def rpath: String = config.rpath
}
