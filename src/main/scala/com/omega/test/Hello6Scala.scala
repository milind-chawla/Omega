package com.omega.test

object Hello6Scala {
    import HelloHelpers._
    
    def main(args: Array[String]): Unit = {
        noexec {
            val f1 = (i: Int) => println((1*i).toString)
            val f2 = (i: Int) => println((2*i).toString)
            val f3 = (i: Int) => println((3*i).toString)
            
            val f = f1 &&& f2 &&& f3
            
            f(5)
        }
        
        noexec {
            val pred = (y: Int) => y < 10
            println( List(1,11,2,22,3,33) filter(pred) )
            
            val pred1 = (y: Int) => y < 11
            val higher: (Int => Boolean) => (Int => Boolean) = (k: Int => Boolean) => pred1 or pred
            
            val aFunc = higher(pred)
            println(aFunc(12))
            println(aFunc(11))
            println(aFunc(10))
        }
    }
}
