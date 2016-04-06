package com.omega.debug

object Debug {
    object debug {
        object on {
            def apply[T](r: => T): Unit = {
                println("DEBUG: " + new java.util.Date + " -> " + r)
            }
        }
        
        object off {
            def apply[T](r: => T): Unit = {
            }
        }
        
        object exec {
            def apply[T](r: => T): T = {
                r
            }
        }
        
        object noexec {
            def apply[T](r: => T): T = {
                null.asInstanceOf[T]
            }
        }
    }
}
