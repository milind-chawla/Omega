package com.omega.util

object JavaList {
    
    def apply[A]() = new java.util.ArrayList[A]
    
    def apply[A](items: A*) = {
        val list = new java.util.ArrayList[A]
        
        items foreach { item =>
            list add item
        }
        
        list
    }
}
