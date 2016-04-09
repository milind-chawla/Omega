package omega

import scala.scalajs.js
import scala.scalajs.js.annotation._

@ScalaJSDefined
@JSExport
class OmegaJS extends js.Object {

	def factorial(i: Int): Int = {

		@scala.annotation.tailrec
		def fact(a: Int, j: Int): Int = j match {
			case 1 => a
			case x => fact(a * x, x - 1)
		}

		fact(1, i)
	}
}
