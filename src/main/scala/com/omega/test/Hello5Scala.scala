package com.omega.test

object Hello5Scala {
    object ReverseList {
        import scala.annotation._
        
        @tailrec
        def reverseList(list: List[Int], acc: List[Int]): List[Int] = list match {
            case head :: tail => reverseList(tail, head :: acc)
            case Nil => acc
        }
    }
    
    def main(args: Array[String]): Unit = {
        import HelloHelpers._
        
        noexec {
            import ReverseList._
        
            val l = 1 to 20000 toList
        
            println(reverseList(l, Nil))    
        }
        
        noexec {
            lazy val p = {
                println("Initializing")
                9
            }
            
            println(p) 
            println(p)
        }
        
        noexec {
            var k = 2
            def f() = { k += 1; k }
            lazy val p = Stream.continually(f())
            
            p take 4 foreach println
            println(p)
        }
        
        noexec {
            lazy val r = Stream.cons(1, Stream.cons(2, Stream.Empty))
            r take 4 foreach println
        }
        
        noexec {
            def s(n: Int): Stream[Int] = Stream.cons(n, s(n + 1))
            lazy val q = s(0)
            
            println(q)
            
            def succ(n: Int): Stream[Int] = n #:: succ(n + 1)
            lazy val r = succ(0)
            
            println(r)
        }
        
        noexec {
            var k = 3
            def f() = {
                println("prevouys value = " + k)
                k += 1
                k
            }
            val p = Stream.continually(f())
            
            p take 3 foreach println
            p take 3 foreach println
        }
        
        exec {
            def fib(m: Int, n: Int): Stream[Int] = (m + n) #:: fib(n, m + n)
            
            val f = fib(0, 1)
            
            println(f)
            
            f take 7 foreach println
            
            println(f)
            
            lazy val fibz: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibz.zip(fibz.tail).map{n => n._1 + n._2}
            
            println(fibz)
        }
        
        exec {
            
        }
    }
}
