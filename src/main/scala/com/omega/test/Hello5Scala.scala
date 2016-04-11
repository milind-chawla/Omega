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
        import ReverseList._
        
        val l = 1 to 20000 toList
        
        println(reverseList(l, Nil))
    }
}
