package com.omega.zhello

object Hello2Scala {
    import HelloHelpers._
    
    def main(args: Array[String]): Unit = {
        noexec {
            val m = List(Some("on"), None, Some("us"), None)
            
            for {
                Some(p) <- m
            } println(p)
        
            println( m.flatten )
        }
        
        noexec {
            def m(n: Option[Int]): Option[Int] = n.map(1+)
            
            val c1 = Some(10)
            val c2 = None
            
            //println( m(c1) )
            //println( m(c2) )
            
            val d1 = c1.map { x => x }
            val d2 = c1.flatMap { x => Some(x*x) }
            
            println( d1 )
            println( d2 )
        }
        
        noexec {
            case class TreeNode(v: Int, left: Option[TreeNode] = None, right: Option[TreeNode] = None) {
                
                def add(av: Int): TreeNode = {
                    insert(av).get    
                }
                
                def insert(av: Int): Option[TreeNode] = {
                    def insertLeft(av: Int) = left.flatMap(_.insert(av)) orElse Option(TreeNode(av))
                    def insertRight(av: Int) = right.flatMap(_.insert(av)) orElse Option(TreeNode(av))
                    
                    av.compare(v) match {
                        case 0 => Option(this)
                        case -1 => Option(TreeNode(v, insertLeft(av), right))
                        case 1 => Option(TreeNode(v, left, insertRight(av)))
                    }
                }
                
                def traverseItInOrder(): Option[List[TreeNode]] = {
                    val leftList = left.flatMap { x => x.traverseItInOrder() }.getOrElse(Nil)
                    val rightList = right.flatMap { x => x.traverseItInOrder() }.getOrElse(Nil)
                    val result = (leftList :+ this) ++ rightList
                    Option(result)
                }
                
                def traverseInOrder(): List[TreeNode] = {
                    val result: Option[List[TreeNode]] = traverseItInOrder()
                    result.getOrElse(Nil)
                }
            }
            
            val p = TreeNode(4).add(3).add(0).add(1).add(99).add(1).add(4)
            
            for {
                q <- p.traverseInOrder() // 5
            } println(q.v)
        }
        
        noexec {
            val scores = List(100, 200, 300)
            
            println( (0 /: scores)(_ + _) )
        }
        
        exec {
            println("Hello world !!!")
        }
    }
}
