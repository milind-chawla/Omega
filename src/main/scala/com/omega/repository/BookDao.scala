package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book

trait BookDao {
    def getBook(id: Long): Option[Book]
    def getBooks: Option[JList[Book]]
    
    def save(book: Book): Option[Book]
}