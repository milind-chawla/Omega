package com.omega.debug

object Debug {
    trait DebugFlag
    trait On
    trait Off
    
    object on extends DebugFlag with On
    object off extends DebugFlag with Off
    
    object debug {
        def apply(t: DebugFlag)(f: => Unit): Unit = t match {
            case `on` => f
            case `off` =>
        }
        
        def apply(s: String): Unit = {
            println("DEBUG: " + new java.util.Date + " -> " + s)
        }
    }
}
