package com.omega.util

import scala.util.control.NonFatal

object OmegaHelpers {
    sealed trait OM
    implicit val om = new OM { }
    
    def imp_g(om: OM): Unit = {
    }
    
    def noexec[T](f: => T)(implicit g: OM => Unit): T = {
        null.asInstanceOf[T]
    }
    
    def exec[T](f: => T)(implicit g: OM => Unit): T = {
        val exe: T => T = (t: T) => { g(implicitly[OM]); t }
        exe(f)
    }
    
    implicit class PrintHelper[T](t: T) {
        def printout: Unit = {
            System.out.println(t)
        }
        
        def printerr: Unit = {
            System.err.println(t)
        }
    }
    
    implicit class StringHelper(s: String) {
        def intValue: Int = {
            try {
                s.toInt
            } catch {
                case NonFatal(ex) =>
                    Int.MinValue
            }
        }
        
        def longValue: Long = {
            try {
                s.toLong
            } catch {
                case NonFatal(ex) =>
                    Long.MinValue
            }
        }
        
        def doubleValue: Double = {
            try {
                s.toDouble
            } catch {
                case NonFatal(ex) =>
                    Double.MinValue
            }
        }
        
        def floatValue: Float = {
            try {
                s.toFloat
            } catch {
                case NonFatal(ex) =>
                    Float.MinValue
            }
        }
    }
}
