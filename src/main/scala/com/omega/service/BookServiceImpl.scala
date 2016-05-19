package com.omega.service

import java.util.{ List => JList }

import org.springframework.transaction.annotation.Transactional

import com.omega.domain.Book
import com.omega.repository.BookDao
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.Table

@Transactional
class BookServiceImpl(val bookDao: BookDao) extends BookService with BeanLifeCycle {
    
    @Transactional
    override def findById(id: Long): Option[Book] = {
        bookDao.findById(id)
    }
    
    @Transactional
    override def getBooks: JList[Book] = {
        bookDao.getBooks
    }
    
    @Transactional
    def getBooks(startid: Long, max: Int): JList[Book] = {
        bookDao.getBooks(startid, max)
    }
    
    @Transactional
    override def save(book: Book): Option[Book] = {
        bookDao.save(book)
    }
    
    @Transactional
    override def update(book: Book): Option[Book] = {
        bookDao.update(book)
    }
}
