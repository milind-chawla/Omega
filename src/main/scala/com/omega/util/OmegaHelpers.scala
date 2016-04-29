package com.omega.util

import scala.util.control.NonFatal
import org.springframework.ui.Model
import javax.servlet.http.HttpServletRequest
import com.omega.util.reflect.CController

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
    
    implicit class ModelMaker[A](model: Model) {
        
        def apply(a: => A): Model = a match {
            case map: Map[_, _] => {
                map.map { case(k, v) =>
                    model.addAttribute(k.toString, v)
                }
                
                model
            }
            case _ => model
        }
        
        def activate[C <: CController](controller: C): Unit = {
            model {
                Map {
                    "activate" -> controller.name
                }
            }
        }
    }
    
    def contextPath(implicit req: HttpServletRequest) = req.getContextPath
}
