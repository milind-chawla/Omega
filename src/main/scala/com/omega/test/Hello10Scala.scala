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
        
        exec {
            
        }
    }
}