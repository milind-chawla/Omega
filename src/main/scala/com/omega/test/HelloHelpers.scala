package com.omega.test

object HelloHelpers {
    
    trait HelloHelpersMarker
    implicit val hh = new HelloHelpersMarker { }
    
    implicit def printline()(implicit mrk: HelloHelpersMarker): Unit = {
        println("-" * 30)
    }
    
    def exec[T](t: => T)(implicit fn: () => Unit, mrk: HelloHelpersMarker): T = {
        def exe(s: T, f: () => Unit): T = {
            f(); s
        }
        
        exe(t, fn)
    }
    
    def noexec[T](r: => T)(implicit fn: () => Unit, mrk: HelloHelpersMarker): T = {
        null.asInstanceOf[T]
    }
}
