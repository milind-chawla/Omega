package com.omega.util

object JavaMap {
    
    def apply[String, B]() = new java.util.HashMap[String, B]
    
    def apply[String, B](tups: (String, B)*): java.util.HashMap[String, B] = {
        val map = new java.util.HashMap[String, B]
        
        tups foreach { case (k, v) =>
            map.put(k, v)
        }
        
        map
    }
}