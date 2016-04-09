package omega

import scala.scalajs.js.JSApp

object TutorialApp extends JSApp {

	class Person(val id: Int, val name: String) {
	}

	object Person {
		def apply(id: Int, name: String) = new Person(id, name)
	}

	def factorial(i: Int): BigInt = {

		@scala.annotation.tailrec
		def fact(a: Int, j: Int): BigInt = j match {
			case 1 => a
			case x => fact(a * x, x - 1)
		}

		fact(1, i)
	}

	def main(): Unit = {
		println(Person(1, "A"))
		println("factorial 10 = " + factorial(10))
	}	
}


object ABC {
	val x = 5
}