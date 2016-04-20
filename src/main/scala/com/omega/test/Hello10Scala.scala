package com.omega.test

object Hello10Scala {
    import scala.annotation._
    import com.omega.util.OmegaHelpers._
    
    def main(args: Array[String]): Unit = {
        implicit def imp_gx(om: OM): Unit = {
            println("*" * 50)
        }
        
        noexec {
            type MoviePref = () => String
            
            abstract class FamilyMember {
                def name: String
                def moviePref: MoviePref 
            }
            
            case class Mom(name: String, moviePref: MoviePref) extends FamilyMember
            case class Dad(name: String, moviePref: MoviePref) extends FamilyMember
            case class Kid(name: String, moviePref: MoviePref) extends FamilyMember
            
            def letUsGoForAMovie[T <: FamilyMember](familyMember: T): Unit = {
                val mp = s"${familyMember.name} : ${familyMember.moviePref() } "
                println(mp)
            }
            
            val mom = Mom("Mom", () => "some romantic flick")
            val dad = Dad("Dad", () => "Action! Guns! Chase")
            val kid = Kid("Kid", () => "Animation for me")
            
            letUsGoForAMovie(mom)
            letUsGoForAMovie(dad)
            letUsGoForAMovie(kid)
        }
        
        noexec {
            def functor[X, Y](f: X => Y): List[X] => List[Y] = {
                def fun: List[X] => List[Y] = (lx: List[X]) => lx match {
                    case Nil => Nil
                    case x :: xs => f(x) :: fun(xs)
                }
                
                def funRec: List[X] => List[Y] = (lx: List[X]) => {
                    @tailrec
                    def fn(lx: List[X], ly: List[Y]): List[Y] = lx match {
                        case Nil => ly
                        case x :: xs => fn(xs, f(x) :: ly)  
                    }
                    fn(lx, List()).reverse
                }
                
                funRec
            }
            
            val p = List("Hi", "there", "sexy", "lady")
            
            def doubleEachChar(s: String) = ((for(c <- s) yield c + "" + c).toList).mkString
            def numberOfLowerCaseChars(s: String) = s.filter { c => c.isLower }.length
            
            val f1 = functor(doubleEachChar)
            val f2 = functor(numberOfLowerCaseChars)
            
            println(f1(p))
            println(f2(p))
        }
        
        noexec {
            import scala.collection.generic.CanBuildFrom
            import scala.collection.mutable.ListBuffer
            
            object MyMap {
                def myMap[X, Y, Container[X] <: Traversable[X]]
                    (collection: Container[X])
                    (f: X => Y)
                    (implicit builderSpec: (CanBuildFrom[Container[Y], Y, Container[Y]])): Container[Y] = {
                    
                    val buildIt = builderSpec()
                    collection foreach { x =>
                        buildIt += f(x)
                    }
                    
                    buildIt.result
                }
            }
            
            import MyMap._
            
            println(myMap(Seq(1,2)) { _ > 1 })
            println(myMap(List(1,2)) { _ < 1 })
            println(myMap(Vector(7, 8, 9)) { _ * 2 })
            println(myMap(ListBuffer(11, 12, 13)) { _ + 1 })
            println(myMap(Set(11, 12, 13)) { _ + 1 })
        }
        
        noexec {
            object Monad {
                def monad[X, Y](f: X => List[Y]): List[X] => List[Y] = {
                    def fun: List[X] => List[Y] = (lx: List[X]) => lx match {
                        case Nil => Nil
                        case x :: xs => f(x) ::: fun(xs)
                    }
                    
                    def funRec: List[X] => List[Y] = (lx: List[X]) => {
                        @tailrec
                        def fn(lx: List[X], ly: List[Y]): List[Y] = lx match {
                            case Nil => ly
                            case x :: xs => fn(xs, f(x) ::: ly)    
                        }
                        
                        fn(lx, List()).reverse
                    }
                    
                    funRec//negi.vivek@gm
                }
            }
            
            import Monad._
            
            def f1(n: Int) = (1 to n).toList
            val f = monad(f1)
            
            println(f(List(7, 8, 9)))
        }
        
        noexec {
            val f1 = (n: Int) => (1 to n).toList map { _ % 2 == 0 }
            
            println(List(2, 3) map f1)
            println(List(2, 3) flatMap f1)
            
            def f(x: Int) = if(x%2 == 0) Some(x) else None
            
            println( f(10) flatMap { x => Some(x + 1) } )
            println( f(11) flatMap { x => Some(x + 1) } )
        }
        
        exec {
            class MyMonoid {
                def iden = ""
                def f(x: String, y: String) = x.concat(y)
            }
            
            val p = new MyMonoid
            
            val list = List("Singing", " In", " The", " Rain")
            
            println( list.foldLeft(p.iden)(p.f) )
        }
    }
}