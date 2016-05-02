package com.omega.zhello

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
        
        noexec {
            def fib(m: Int, n: Int): Stream[Int] = (m + n) #:: fib(n, m + n)
            
            val f = fib(0, 1)
            
            println(f)
            
            f take 7 foreach println
            
            println(f)
            
            lazy val fibz: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fibz.zip(fibz.tail).map{n => n._1 + n._2}
            
            println(fibz)
        }
        
        noexec {
            var k = 0
            
            def getVal(): Int = {
                k += 1
                k
            }
            
            def g(i: Int): Unit = {
                println(s"${i}")
                println(s"${i}")
                println(s"${i}")
            }
            
            def f(i: => Int): Unit = {
                println(s"${i}")
                println(s"${i}")
                println(s"${i}")
            }
            
            //g(getVal())
            f(getVal())
        }
        
        noexec {
             def succ(n: Int): Stream[Int] = n #:: succ(n + 1)
             lazy val r = succ(0)
             
             println(r take 10 mkString "+")
        }
        
        noexec {
            def numStream(n: Int): Stream[Int] = Stream.from(n)
            def sieve(stream: Stream[Int]): Stream[Int] = stream.head #:: sieve((stream.tail).filter(x => x % stream.head != 0))
            
            val s = numStream(2)
            
            s take 5 foreach println
            
            val p = sieve(s)
            println(p)
            
            p take 5 foreach println
        }
        
        noexec {
            object Sieve {
                def f(s: Stream[Int], head: Int): Stream[Int] = {
                    val r = s filter { x =>
                        if(x % head != 0) {
                            println(s"${x} is not evenly divisible by ${head}")
                            true
                        } else {
                            println(s"${x} is evenly divisible by ${head}")
                            println(s"Discarding ${x}")
                            false
                        }
                    }
                    r
                }
                
                def numStream(n: Int): Stream[Int] = Stream.from(n)
                
                def sieve(stream: Stream[Int]): Stream[Int] = stream.head #:: sieve(f(stream.tail, stream.head))
                
                val p = sieve(numStream(2))
            }
            
            Sieve.p take 5 foreach println
        }
        
        exec {
            val p = List("", "9#greaT", "is great", "greater", "23#pp", "Aa#@4")
            println(p)
            
            val pp = p.filterNot (_.isEmpty)
            .filter (_.exists(_.isDigit))
            .filter (_.exists(_.isUpper))
            .filter (_.matches("""^.*[\W].*$"""))
            .filter (_.length >= 5)
            
            println(pp)
            
            val v = p.view
            println(v)
            
            val pv = v.filterNot (_.isEmpty)
            .filter (_.exists(_.isDigit))
            .filter (_.exists(_.isUpper))
            .filter (_.matches("""^.*[\W].*$"""))
            .filter (_.length >= 5)
            
            println(pv)
            println(pv.take(pv.length).force)
        }
        
        exec {
            val p = List("", "9#greaT", "is great", "greater", "23#pp", "Aa#@4")
            
            def working(s: String):Unit = println(s"Working on ${s}")
            def isEmpty(s: String): Boolean = {
                working(s)
                s.isEmpty
            }
            def hasOneDigit(s: String): Boolean = {
                working(s)
                s.exists(_.isDigit)
            }
            def hasOneUpperCaseChar(s: String): Boolean = {
                working(s)
                s.exists(_.isUpper)
            }
            def matches(s: String): Boolean = {
                working(s)
                s.matches( """^.*[\W].*$""")
            }
            def hasMinLen(s: String): Boolean = {
                working(s)
                s.length >= 5
            }
            
            val v = p.view
            
            val pv = v.filterNot (isEmpty)
            .filter (hasOneDigit)
            .filter (hasOneUpperCaseChar)
            .filter (matches)
            .filter (hasMinLen)
            
            val x = pv take pv.length
            println(x force)
        }
    }
}
