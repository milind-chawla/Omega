package com.omega.test

object Hello2Scala {
    import HelloHelpers._
    
    def main(args: Array[String]): Unit = {
        exec {
            val m = List(Some("on"), None, Some("us"), None)
            
            for {
                Some(p) <- m
            } println(p)
        
            println( m.flatten )
        }
        
        exec {
            def m(n: Option[Int]): Option[Int] = n.map(1+)
            
            val c1 = Some(10)
            val c2 = None
            
            println( m(c1) )
            println( m(c2) )
        }
        
        exec {
            case class TreeNode()
            
            println(new TreeNode)
        }
    }
}