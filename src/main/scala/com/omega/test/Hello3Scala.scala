package com.omega.test

object Hello3Scala {
    import HelloHelpers._
    import scala.annotation.tailrec
    
    def main(args: Array[String]): Unit = {
        
        noexec {
            def count(list: List[Int]): Int = list match {
                case Nil => 0
                case head :: tail => 1 + count(tail)
            }
            
            val l = 1 to 20000
            println(count(l.toList))
        }
        
        noexec {
            case class MyClass(x: Int, y: Int)
            
            val p = MyClass(1, 2)
            
            p match {
                case MyClass(a, b) => println(s"a=$a, b=$b")
            }
            
            p match {
                case a MyClass b => println(s"a=$a, b=$b")
            }
            
            val p2 = List(1, 2, 3, 4)
            
            p2 match {
                case ::(head, tail) => println(s"head=$head, tail=$tail")
                case _ => println("What's up???")
            }
        }
        
        noexec {
            def count(list: List[Int]): Int = {
                @tailrec
                def countIt(l: List[Int], acc: Int): Int = l match {
                    case Nil => acc
                    case head :: tail => countIt(tail, acc + 1)
                }
                
                countIt(list, 0)
            }
            
            val l = (1 to 20000).toList
            println(count(l))
        }
        
        exec {
            def nth(list: List[Int], n: Int): Option[Int] = {
                @tailrec
                def nthElem(list: List[Int], acc: (Int, Int)): Option[Int] = list match {
                    case Nil => None
                    case head :: tail => {
                        if(acc._1 == acc._2) Option(head)
                        else nthElem(tail, (acc._1 + 1, acc._2))
                    }
                }
                
                nthElem(list, (0, n))
            }
            
            val bigList = 1 to 100000 toList
            
            println(nth(List(1, 2, 3, 4, 5, 6), 3).getOrElse("No such elem"))
            println(nth(List(1, 2, 3, 4, 5, 6), 300).getOrElse("No such elem"))
            println(nth(bigList, 2333).getOrElse("No such elem"))
        }
    }
}