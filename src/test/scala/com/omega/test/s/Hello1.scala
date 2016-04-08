package com.omega.test.s

object Hello1 {
    def main(args: Array[String]): Unit = {
        var list = List(1, 2, 3, 4, 5, 9, 11, 20, 21, 22)
        
        println( groupNumbersRec(list, (i, j) => i - j == 1) )
        println( groupNumbersSz(list, (i, j) => j - i == 1) )
        
        list = List(1, 3, 4, 6, 8, 9)
        
        println( groupNumbersSz(list, (i, j) => j - i == 2) )
        
        println("*" * 31)
        
        val d1 = List(1, 2, 3, 4, 5)
        val d2 = List(11, 22, 33, 44, 55)
        
        println( (d1, d2).zipped map (_ + _) )
        println( (1 to 100).map(2*).filter { x => x%3==0 && x%4==0 && x%5==0 } )
    }
    
    def groupNumbers(list: List[Int]): List[List[Int]] = {
        
        def groupThem(lst: List[Int], acc: List[Int]): List[List[Int]] = lst match {
            case Nil => acc.reverse :: Nil
            case x :: xs => acc match {
                case Nil => groupThem(xs, x :: acc)
                case y :: ys if(x - y == 1) => groupThem(xs, x :: acc)
                case _ => acc.reverse :: groupThem(xs, x :: List())
            }
        }
        
        groupThem(list, List())
    }
    
    def groupNumbersRec(list: List[Int], f: (Int, Int) => Boolean): List[List[Int]] = {
        
        @scala.annotation.tailrec
        def groupThem(lst: List[Int], result: List[List[Int]], acc: List[Int]): List[List[Int]] = lst match {
            case Nil => acc.reverse :: result
            case x :: xs => acc match {
                case Nil => groupThem(xs, result, x :: acc)
                case y :: ys if(f(x, y)) => groupThem(xs, result, x :: acc)
                case k => groupThem(xs, acc.reverse :: result, x :: List())
            }
        }
        
        groupThem(list, List(), List()).reverse
    }
    
    def groupNumbersSz(list: List[Int], f: (Int, Int) => Boolean): List[_] = {
        import scalaz.syntax.std.list._
        
        list groupWhen f
    }
}
