package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book

trait BookDao {
    def findById(id: Long): Option[Book]
    def getBooks: JList[Book]
    
    def save(book: Book): Option[Book]
    def update(book: Book): Option[Book]
}