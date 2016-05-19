package com.omega.service

import java.util.{ List => JList }

import com.omega.domain.Book

import javax.persistence.Entity

trait BookService {
    def findById(id: Long): Option[Book]
    
    def getBooks: JList[Book]
    def getBooks(startid: Long, max: Int): JList[Book]
    
    def save(book: Book): Option[Book]
    def update(book: Book): Option[Book]
}
