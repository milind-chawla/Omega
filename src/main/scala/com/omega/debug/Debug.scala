package com.omega.debug

object Debug {
    trait DebugFlag
    trait On
    trait Off
    
    object on extends DebugFlag with On
    object off extends DebugFlag with On
    
    object debug {
        def apply(t: DebugFlag)(f: => Unit): Unit = t match {
            case `on` => print("DEBUG: " + new java.util.Date + " -> "); f
            case `off` =>
        }
    }
}
