package com.omega.test

object TestGroupScala {
    def main(args: Array[String]): Unit = {
        println(groupNumbersRec(List(1, 2, 3, 4, 5, 9, 11, 20, 21, 22))((i, j) => i - j == 1))
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
    
    def groupNumbersRec(list: List[Int])(f: (Int, Int) => Boolean): List[List[Int]] = {
        
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
}
