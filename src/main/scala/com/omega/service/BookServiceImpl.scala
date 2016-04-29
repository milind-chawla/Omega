package com.omega.service

import java.util.{ List => JList }
import java.util.{ ArrayList => JArrayList }

import org.springframework.transaction.annotation.Transactional

import com.omega.domain.Book
import com.omega.repository.BookDao
import com.omega.util.BeanLifeCycle
import scala.util.Try
import scala.util.Failure
import scala.util.Success

@Transactional
class BookServiceImpl(val bookDao: BookDao) extends BookService with BeanLifeCycle {
    
    @Transactional
    override def getBooks: Option[JList[Book]] = {
        Try(bookDao.getBooks) match {
            case Success(books) => books
            case Failure(fail) => println(fail); None
        }
    }
    
    @Transactional
    override def getBook(id: Long): Option[Book] = {
        Try(bookDao.getBook(id)) match {
            case Success(book) => book
            case Failure(fail) => println(fail); None
        }
    }
    
    @Transactional
    override def save(book: Book): Option[Book] = {
        Try(bookDao.save(book)) match {
            case Success(savedBook) => savedBook
            case Failure(fail) => println(fail); Option(book)
        }
    }
}
