package com.omega.test

object Hello8Scala {
    object IceCreams {
        sealed trait IceCreamType {
            def price: Double
        }
        
        case object Vanilla extends IceCreamType {
            val price = 10.0
        }
        
        case object Mango extends IceCreamType {
            val price = 20.0
        }
        
        implicit def iceCreamPriceWrapper(iceCreamType: IceCreamType): Double = iceCreamType.price
        
        case class IceCream(price: Double)
        def add(c: IceCream)(p: Double) = c.copy(price = c.price + p)
        
        def addNuts(c: IceCream) = add(c)(15)
        def addJelly(c: IceCream) = add(c)(25)
        def addHoney(c:IceCream) = add(c)(30)
        
        val withNuts = addNuts _
        val withJelly = addJelly _
        val withHoney = addHoney _
    }
    
    def main(args: Array[String]): Unit = {
        import HelloHelpers._
        
        noexec {
            import IceCreams._
        
            val order1 = withJelly(withNuts(withHoney(IceCream(Vanilla))))
            println(order1.price)
        
            val order2 = withJelly(withNuts(IceCream(Mango)))
            println(order2.price)    
        }
        
        exec {
            object Palindrome {
                def findp(s: String): Vector[(String, (Int, Int))] = {
                    var v = Vector.empty[(String, (Int, Int))]
                    
                    for {
                        i <- 1 to s.length - 1
                        j <- i to s.length - 1
                    } {
                        val sub = s.substring(i, j)
                        
                        if(sub == sub.reverse && sub.length > 2) {
                            v = v :+ (sub, (i, j))
                        }
                    }
                    
                    v
                }
            }
            
            val s = "abcdefffeabcdffgghhggff"
            val v = Palindrome.findp(s)
            
            for(x <- v) {
                println(s.substring(x._2._1, x._2._2))    
            }
            
            println(v.maxBy(f => f._2._2 - f._2._1))
        }
    }
}