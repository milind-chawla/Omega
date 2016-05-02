package com.omega.util

import scala.util.control.NonFatal
import org.springframework.ui.Model
import javax.servlet.http.HttpServletRequest
import com.omega.controllers.CController

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
    
    implicit class ModelMaker(model: Model) {
        
        def apply(m: => Map[_, _]): Model = {
            m.map { case(k, v) =>
                model.addAttribute(k.toString, v)
            }
            
            model
        }
        
        def apply[C <: CController](c: C)(implicit req: HttpServletRequest): Model = {
            model {
                Map[Any, Any]() + 
                ("activate" -> s"${c.lname}") + 
                ("path" -> s"${c.path}") + 
                ("path_new" -> s"${c.path_new}")
            }
            
            model {
                Map[Any, Any]() +
                ("homePath" -> "com.omega.controllers.HomeController".controllerPath) +
                ("homeName" -> "com.omega.controllers.HomeController".controllerName) +
                ("booksPath" -> "com.omega.controllers.BooksController".controllerPath) +
                ("booksName" -> "com.omega.controllers.BooksController".controllerName)
            }
        }
    }
    
    implicit class ControllerClassNameHelper(controllerClassName: String) {
        
        def controllerName: String = {
            val n = controllerClassName.split("\\.").last
            val i = n.indexOf("Controller")
            n.substring(0, i)
        }
        
        def controllerPath(implicit req: HttpServletRequest): String = {
            val n = controllerName.toLowerCase
            s"${req.getContextPath}/${n}/index"
        }
    }
}
