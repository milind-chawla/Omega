package com.omega.test

object HelloHelpers {
    
    trait HelloHelpersMarker
    implicit val hh = new HelloHelpersMarker { }
    
    implicit def printline(mrk: HelloHelpersMarker): Unit = {
        println("-" * 70)
    }
    
    def exec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): T = {
        val exe: T => T = (s: T) => { fn(implicitly[HelloHelpersMarker]); s }
        exe(t)
    }
    
    def noexec[T](t: => T)(implicit fn: (HelloHelpersMarker) => Unit): T = {
        null.asInstanceOf[T]
    }
    
    implicit class FunctionCombiner[T](fn: T => Boolean) {
        def <&&&>(f: T => Boolean)(implicit mrk: HelloHelpersMarker): T => Boolean = {
            (v: T) => (fn(v) && f(v))
        }
        
        def <|||>(f: T => Boolean)(implicit mrk: HelloHelpersMarker): T => Boolean = {
            (v: T) => (fn(v) || f(v))
        }
    }
    
    implicit class FunctionCombiner2[T, U](fn: T => U) {
        def &&&(f: T => U)(implicit mrk: HelloHelpersMarker): T => U = {
            (t: T) => fn(t); f(t)
        }
    }
    
    implicit class Tuple2Helper[T, U](tup: Tuple2[Seq[T], Seq[U]]) {
        def zip(implicit mrk: HelloHelpersMarker) = tup._1 zip tup._2
    }
}
