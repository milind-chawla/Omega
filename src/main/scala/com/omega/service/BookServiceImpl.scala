package com.omega.service

import java.util.{ List => JList }

import org.springframework.transaction.annotation.Transactional

import com.omega.domain.Book
import com.omega.repository.BookDao
import com.omega.util.BeanLifeCycle

@Transactional
class BookServiceImpl(val bookDao: BookDao) extends BookService with BeanLifeCycle {
    
    @Transactional
    override def save(book: Book): Book = {
        bookDao.save(book)
    }
    
    @Transactional
    override def getBooks: JList[Book] = {
        bookDao.getBooks
    }
}
