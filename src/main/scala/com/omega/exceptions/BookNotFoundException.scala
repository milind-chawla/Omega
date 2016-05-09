package com.omega.exceptions

case class BookNotFoundException(bookId: String) extends Exception {
}
