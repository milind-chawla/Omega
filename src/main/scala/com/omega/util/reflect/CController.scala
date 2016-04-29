package com.omega.util.reflect

trait CController {
    def name: String = {
        val n = this.getClass.getCanonicalName.split("\\.").last
        val i = n.indexOf("Controller")
        
        n.substring(0, i).toLowerCase
    }
}