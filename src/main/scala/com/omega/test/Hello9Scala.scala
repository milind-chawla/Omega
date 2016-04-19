package com.omega.test

object Hello9Scala {
    import HelloHelpers._
    
    def main(args: Array[String]): Unit = {
        noexec {
            val list = List.fill(10)(0)
            println(list)
            
            list map { a => a + 1 } foreach { a => print(" " + a) }
            println()
        }
        
        noexec {
            val passwords = List("oddoddboy@thedoor", "likelynew", "alwaysThinkOfMyself", "naughty")
            
            def lengthGreaterThan(minLength: Int)(s: String) = s.length > minLength
            def containsChar(c: Char, s: String) = s.contains(c)
            def containsOneOf(set: Seq[Char])(s: String) = set exists { c => containsChar(c, s) }
            
            val validatePasswdLen = lengthGreaterThan(6) _
            val containsUpperCaseChar = containsOneOf('A' to 'Z') _
            val containsLowerCaseChar = containsOneOf('a' to 'z') _
            
            val arr = Array.fill(passwords.length)(false)
            var res = passwords zip arr
            println(res)
            
            passwords.indices.foreach { i =>
                arr(i) = validatePasswdLen(passwords(i)) && 
                        containsUpperCaseChar(passwords(i)) &&
                        containsLowerCaseChar(passwords(i))
            }
            res = passwords zip arr
            println(res)
            
            passwords.zipWithIndex foreach { case (w, i) =>
                arr(i) = validatePasswdLen(w) && 
                        containsUpperCaseChar(w) &&
                        containsLowerCaseChar(w)
            }
            res = passwords zip arr
            println(res)
            
            val checker = (w: String) => (w, validatePasswdLen(w) && containsUpperCaseChar(w) && containsLowerCaseChar(w))
            
            res = passwords map checker
            println(res)
            
            res = for(w <- passwords) yield checker(w)
            println(res)
            
            val checker2 = (w: String) => (validatePasswdLen(w) && containsUpperCaseChar(w) && containsLowerCaseChar(w))
            
            val r = passwords.foldLeft (List.empty[String], List.empty[Boolean])((b, a) => (a :: b._1, checker2(a) :: b._2)).zip
            println(r)
            
            type PasswdChecker = (String, Boolean) => (String, Boolean)
            
            val f1: PasswdChecker = (x: String, b: Boolean) => (x, containsLowerCaseChar(x))
            val f2: PasswdChecker = (x: String, b: Boolean) => (x, containsUpperCaseChar(x))
            val f3: PasswdChecker = (x: String, b: Boolean) => (x, validatePasswdLen(x))
            
            val f = f1.tupled andThen f2.tupled andThen f3.tupled
            
            res = passwords map { p =>
                f((p, false))
            }
            println(res)
        }
        
        noexec {
            val m = 1 to 3 toList
            val n = 1 to 3 toList
            
            val p1 = for(i <- m; j <- n) yield(i + "" + j)
            println(p1)
            
            val p2 = m map { i =>
                n map { j =>
                    i + "" + j
                }
            }
            println(p2)
            
            val p3 = m flatMap { i =>
                n map { j =>
                    i + "" + j
                }
            }
            println(p3)
            
            val o = 1 to 2 toList
            
            val p4 = for(i <- m; j <- n; k <- o) yield(i + "" + j + "" + k)
            println(p4)
            
            val p5 = m flatMap { i =>
                n flatMap { j =>
                    o map { k =>
                        i + "" + j + "" + k
                    }
                }
            }
            println(p5)
        }
        
        noexec {
            val l = List(1, "this", 2, 4.4, 'c')
            
            var p1 = l map {
                case i: Int => Some(i)
                case _ => None
            }
            println(p1)
            
            var p2 = l flatMap {
                case i: Int => Some(i)
                case _ => None
            }
            println(p2)
            
            val m = 1 to 9 toList
            val n = Map(1 -> 7, 15 -> 11, 9 -> 22)
            
            val p3 = for {
                i <- m
                k <- n get i
            } yield k
            println(p3)
            
            val p4 = m flatMap { i =>
                n get i map { k =>
                    k
                }
            }
            println(p4)
        }
        
        exec {
            val l = List(1, 2, 3, 4)
            
            // val s = l reduceLeft { (acc, n) => acc + n }
            // println(s)
            
            val nums = (1 to 20000).toList map { _.toString }
            
            var s = nums.reduceLeft { (acc, n) =>
                acc + "-" + n
            }
            println(s)
            
            s = nums.reduceRight { (acc, n) =>
                acc + "-" + n
            }
            println(s)
        }
    }
}
