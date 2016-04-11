package com.omega.test

object Hello4Scala {
    object Parser {
        import scala.annotation._
        
        val Number = """^(\d+).*""".r
        val LParen = """^[(].*""".r
        
        def factor(f: String): (String, Int) = f match {
            case Number(d) => (f.drop(d.length), d.toInt)
            case LParen(_*) => {
                val p = expr(f.drop(1), 0)
                val e = p._1
                
                if(e.take(1) == ")") {
                    (e.drop(1), p._2)
                } else {
                    throw new IllegalArgumentException("Right bracket missing")    
                }
            }
            case _ => throw new IllegalArgumentException("Number or sub-expression expected")
        }
        
        @tailrec
        def term(t: String, acc: Int): (String, Int) = {
            val p = factor(t)
            val e = p._1
            
            if(e.take(1) == "*") {
                term(e.drop(1), acc * p._2)
            } else {
                (e, acc * p._2)
            }
        }
        
        @tailrec
        def expr(s: String, acc: Int): (String, Int) = {
            val p = term(s, 1)
            val e = p._1
            
            if(e.take(1) == "+") {
                expr(e.drop(1), acc + p._2)
            } else {
                (e, acc + p._2)
            }
        }
        
        def expr(s: String): Int = {
            val e = expr(s, 0)
            e._2
        }
    }
    
    def main(args: Array[String]): Unit = {
        import Parser._
        
        val p = expr("(1+2)*3*(2+4)")
        println(p)
    }
}