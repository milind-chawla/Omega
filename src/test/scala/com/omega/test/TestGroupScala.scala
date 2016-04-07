package com.omega.test

object TestGroupScala {
    def main(args: Array[String]): Unit = {
        val i = List[Int]() :: Nil
        println(i)
        
        println(groupNumbers(List(1, 2, 3, 4, 5, 9, 11, 20, 21, 22)))
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
}