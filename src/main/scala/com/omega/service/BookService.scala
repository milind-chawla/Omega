package com.omega.service

import java.util.{ List => JList }

import com.omega.domain.Book

import javax.persistence.Entity

trait BookService {
    def getBook(id: Long): Option[Book]
    def getBooks: JList[Book]
    
    def save(book: Book): Book
}
