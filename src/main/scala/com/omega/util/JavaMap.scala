package com.omega.util

object JavaMap {
    
    def apply[A, B]() = new java.util.HashMap[A, B]
    
    def apply[A, B](tups: (A, B)*): java.util.HashMap[A, B] = {
        val map = new java.util.HashMap[A, B]
        
        tups foreach { case (k, v) =>
            map.put(k, v)
        }
        
        map
    }
}