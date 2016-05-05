package com.omega.service

import java.util.{ List => JList }

import com.omega.domain.Book

import javax.persistence.Entity

trait BookService {
    def getBook(id: Long): Option[Book]
    def getBooks: Option[JList[Book]]
    
    def save(book: Book): (Option[Book], Map[String, List[String]])
    def update(book: Book): (Option[Book], Map[String, List[String]])
}
