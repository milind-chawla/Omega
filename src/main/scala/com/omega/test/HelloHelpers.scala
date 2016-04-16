package com.omega.test

object HelloHelpers {
    
    trait HelloHelpersMarker
    implicit val hh = new HelloHelpersMarker { }
    
    implicit def printline(mrk: HelloHelpersMarker): Unit = {
        println("-" * 30)
    }
    
    def exec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): T = {
        val exe: T => T = (s: T) => { fn(implicitly[HelloHelpersMarker]); s }
        exe(t)
    }
    
    def noexec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): T = {
        null.asInstanceOf[T]
    }
    
    implicit class FunctionCombiner[T](fn: T => Boolean) {
        def <&&&>(f: T => Boolean): T => Boolean = {
            (v: T) => (fn(v) && f(v))
        }
        
        def <|||>(f: T => Boolean): T => Boolean = {
            (v: T) => (fn(v) || f(v))
        }
    }
    
    implicit class FunctionCombiner2[T, U](fn: T => U) {
        def &&&(f: T => U): T => U = {
            (t: T) => fn(t); f(t)
        }
    }
}
