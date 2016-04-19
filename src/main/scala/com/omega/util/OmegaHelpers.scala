package com.omega.util

object OmegaHelpers {
    sealed trait OM
    implicit val om = new OM { }
    
    implicit def imp_g(om: OM): Unit = {
    }
    
    def noexec[T](f: => T)(implicit g: OM => Unit): T = {
        null.asInstanceOf[T]
    }
    
    def exec[T](f: => T)(implicit g: OM => Unit): T = {
        val exe: T => T = (t: T) => { g(implicitly[OM]); t }
        exe(f)
    }
    
    /*def main(args: Array[String]): Unit = {
        exec {
            println("Hi")
            123
        }
    }*/
}
