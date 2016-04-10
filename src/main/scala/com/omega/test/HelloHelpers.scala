package com.omega.test

object HelloHelpers {
    
    trait HelloHelpersMarker
    implicit val hh = new HelloHelpersMarker { }
    
    implicit def printline(mrk: HelloHelpersMarker): Unit = {
        println("-" * 30)
    }
    
    def exec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): Option[T] = {
        val exe: T => T = (s: T) => { fn(implicitly[HelloHelpersMarker]); s }
        Option(exe(t))
    }
    
    def noexec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): Option[T] = {
        None
    }
}
