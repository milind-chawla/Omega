package com.omega.controllers

import com.omega.context.ApplicationContextProvider
import com.omega.util.JavaList

trait ControllerConfig {
    def id: String = ???
    def name: String = ???
    def view(v: String): String = ???
    def apath: String = ???
    def rpath: String = ???
}

object ControllerConfig {
    private[this] def contextPath: String = {
        ApplicationContextProvider.getServletContext.getContextPath
    }
    
    object RootControllerConfig extends ControllerConfig {
        override def id: String = "root"
        override def name: String = "Root"
        override def view(v: String): String = s"$id/$v"
        override def apath: String = s"$contextPath/$id"
        override def rpath: String = s"/$id"
    }
    
    object HomeControllerConfig extends ControllerConfig {
        override def id: String = "home"
        override def name: String = "Home"
        override def view(v: String): String = s"$id/$v"
        override def apath: String = s"$contextPath/$id"
        override def rpath: String = s"/$id"
    }
    
    object BooksControllerConfig extends ControllerConfig {
        override def id: String = "books"
        override def name: String = "Books"
        override def view(v: String): String = s"$id/$v"
        override def apath: String = s"$contextPath/$id"
        override def rpath: String = s"/$id"
    }
    
    object AngularSeedControllerConfig extends ControllerConfig {
        override def id: String = "angularseed"
        override def name: String = "AngularSeed"
        override def view(v: String): String = s"$id/$v"
        override def apath: String = s"$contextPath/$id"
        override def rpath: String = s"/$id"
    }
    
    def controllerLinkage = {
        JavaList(
            (RootControllerConfig.id, RootControllerConfig.name, RootControllerConfig.apath),
            (HomeControllerConfig.id, HomeControllerConfig.name, HomeControllerConfig.apath),
            (BooksControllerConfig.id, BooksControllerConfig.name, BooksControllerConfig.apath),
            (AngularSeedControllerConfig.id, AngularSeedControllerConfig.name, AngularSeedControllerConfig.apath)
        )
    }
}