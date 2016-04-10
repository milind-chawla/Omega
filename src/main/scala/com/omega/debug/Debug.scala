package com.omega.debug

object Debug {
    trait DebugMarker
    implicit val dm = new DebugMarker { }
    
    object debug {
        object on {
            def apply[T](r: => T)(implicit mrk: DebugMarker): Unit = {
                println("DEBUG: " + new java.util.Date + " -> " + r)
            }
        }
        
        object off {
            def apply[T](r: => T)(implicit mrk: DebugMarker): Unit = {
            }
        }
        
        object exec {
            def apply[T](r: => T)(implicit mrk: DebugMarker): T = {
                r
            }
        }
        
        object noexec {
            def apply[T](r: => T)(implicit mrk: DebugMarker): T = {
                null.asInstanceOf[T]
            }
        }
    }
}
