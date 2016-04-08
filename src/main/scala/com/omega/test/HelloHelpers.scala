package com.omega.test

object HelloHelpers {
    
    trait HelloHelpersMarker
    implicit val hh = new HelloHelpersMarker { }
    
    implicit def printline()(implicit mrk: HelloHelpersMarker): Unit = {
        println("-" * 30)
    }
    
    def exec[T](t: => T)(implicit fn: () => Unit): T = {
        fn(); t
    }
    
    def noexec[T](r: => T)(implicit fn: () => Unit): T = {
        null.asInstanceOf[T]
    }
}
