package com.omega.util

import scala.util.control.NonFatal

object OmegaHelpers {
    sealed trait OM
    implicit val om = new OM { }
    
    def imp_g(om: OM): Unit = {
    }
    
    def noexec[A](a: => A)(implicit g: OM => Unit): A = {
        null.asInstanceOf[A]
    }
    
    def exec[A](a: => A)(implicit g: OM => Unit): A = {
        val exe: A => A = (a2: A) => { g(implicitly[OM]); a2 }
        exe(a)
    }
    
    implicit class PrintHelper[A](a: A) {
        def printout: Unit = {
            System.out.println(a)
        }
        
        def printerr: Unit = {
            System.err.println(a)
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
