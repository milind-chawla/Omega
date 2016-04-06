package com.omega.service

import java.util.{ List => JList }

import com.omega.domain.Book

import javax.persistence.Entity

trait BookService {
    def save(book: Book): Book
    def getBooks: JList[Book]
}
