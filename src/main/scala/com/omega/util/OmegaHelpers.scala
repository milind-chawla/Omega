package com.omega.util

import scala.util.control.NonFatal

import org.springframework.ui.Model

import com.omega.controllers.CController

import javax.servlet.http.HttpServletRequest
import com.omega.controllers.ControllerSpace
import org.springframework.web.servlet.ModelAndView

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
                    -1.asInstanceOf[Int]
            }
        }
        
        def longValue: Long = {
            try {
                s.toLong
            } catch {
                case NonFatal(ex) =>
                    -1.asInstanceOf[Long]
            }
        }
        
        def doubleValue: Double = {
            try {
                s.toDouble
            } catch {
                case NonFatal(ex) =>
                    -1.asInstanceOf[Double]
            }
        }
        
        def floatValue: Float = {
            try {
                s.toFloat
            } catch {
                case NonFatal(ex) =>
                    -1.asInstanceOf[Float]
            }
        }
        
        def printSpecial: Unit = {
            val len = s.length
            println("-" * (len + 8))
            println("|   " + s + "   |")
            println("-" * (len + 8))
        }
        
        def compact: String = {
            if(s == null) "" else s.trim()
        }
        
        def isEmpty: Boolean = {
            if(s.compact == "") true else false
        }
    }
    
    object UnionTypes {
        type ¬[A] = A => Nothing
        type ¬¬[A] = ¬[¬[A]]
        type ∨[T, U] = ¬[¬[T] with ¬[U]]
        type |∨|[T, U] = { type λ[X] = ¬¬[X] <:< (T ∨ U) }
    }
}
