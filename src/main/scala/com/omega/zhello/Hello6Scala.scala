package com.omega.zhello

object Hello6Scala {
    import HelloHelpers._
    
    def main(args: Array[String]): Unit = {
        noexec {
            val f1 = (i: Int) => 1*i
            val f2 = (i: Int) => 2*i
            val f3 = (i: Int) => 3*i
            
            val f = f1 &&& f2 &&& f3
            f(5)
        }
        
        noexec {
            val pred = (y: Int) => y < 10
            println( List(1,11,2,22,3,33) filter(pred) )
            
            val pred1 = (y: Int) => y < 11
            val higher: (Int => Boolean) => (Int => Boolean) = (k: Int => Boolean) => pred1 <|||> pred
            
            val aFunc = higher(pred)
            println(aFunc(12))
            println(aFunc(11))
            println(aFunc(10))
        }
        
        noexec {
            implicit def housewife = "Housewife"
            def f(momName: String)(implicit worksAs: String) = println(s"Mom ${momName} works as ${worksAs}")
            
            f("Sheela")
            f("Nisha")("Software Engineer")
            
            def matches(n: Int)(f: Int => Boolean): Unit = {
                if(f(n))
                    println("Matches")
                else
                    println("Nope")
            }
            
            matches(4)(_ == 4)
        }
        
        exec {
            import scala.io._
            
            def autoCleanUp[T](f: Source)(handler: Source => T): T = {
                try {
                    handler(f)
                } finally {
                    println("Closing resource")
                    f.close()
                }
            }
            
            val s1 = Source.fromFile(".gitignore")
            val s2 = Source.fromFile(".gitignore")
            
            autoCleanUp(s1) { h =>
                for(line <- h.getLines) {
                    println(line.reverse)
                }
            }
            
            autoCleanUp(s2) { h =>
                for(line <- h.getLines) {
                    println(line.toCharArray.length + ":" + line)
                }
            }
        }
    }
}
