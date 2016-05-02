package com.omega.zhello

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
        
        noexec {
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
        
        noexec {
            val f = (x: Int, y: Int) => x / y
            
            println(f(12, 3))
            //println(f(12, 0))
            
            val g: PartialFunction[(Int, Int), Int] = {
                case (i, j) if j != 0 => i / j
            }
            
            println(g.isDefinedAt(10, 0))
            println(g.isDefinedAt(10, 10))
            
            println(g(10, 5))
        }
        
        noexec {
            val empList = List(("Roy", 100), ("Sunil", 200), ("Atul", 120))
            
            println(
                empList filter (e => e._2 > 100) map (e => e._1)
            )
            
            println(
                empList filter { case (_, bonus) => bonus > 100 } map { case (name, _) => name }
            )
        }
        
        noexec {
            val f: PartialFunction[Int, Int] = { case i if i > 0 => i * i }
            
            println( f.lift(10) )
            println( f.lift(-1) )
        }
        
        exec {
            case class Task(description: String)
            
            type TaskHandler = PartialFunction[Task, Unit]
            
            def canHandle(task: Task, phrases: String*): Boolean = phrases exists (task.description.toLowerCase.contains(_))
            def handleIt(name: String, task: Task): Unit = println(s"${name} Handling: " + task.description)
            
            val dad: TaskHandler = {
                case task: Task if canHandle(task, "wood", "hunt") => handleIt("Dad", task)
            }
            
            val mom: TaskHandler = {
                case task: Task if canHandle(task, "sew", "cook") => handleIt("Mom", task)
            }
            
            val kid: TaskHandler = {
                case task: Task if canHandle(task, "dog", "cat") => handleIt("Kid", task)
            }
            
            val f = dad orElse mom orElse kid
            
            f(Task("Feed the cat"))
            
            val taskList = List("sew up a shirt button", "Walk the Dog", "cut some firewood", "feed the cat", "feed the parrot")
            
            taskList map (Task(_)) collect f
            taskList map (Task(_)) flatMap { x => f.lift(x) }
        }
    }
}