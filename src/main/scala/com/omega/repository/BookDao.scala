package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book

trait BookDao {
    def save(book: Book): Book
    def getBooks: JList[Book]
}