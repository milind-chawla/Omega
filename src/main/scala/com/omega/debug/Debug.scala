package com.omega.debug

object Debug {
    object debug {
        object on {
            def apply(s: String): Unit = {
                println("DEBUG: " + new java.util.Date + " -> " + s)
            }
        }
        
        object off {
            def apply(s: String): Unit = {
            }
        }
    }
}
