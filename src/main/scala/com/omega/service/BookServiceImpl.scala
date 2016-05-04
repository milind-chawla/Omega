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
class BookServiceImpl(val actorService: ActorService, val bookDao: BookDao) extends BookService with BeanLifeCycle {
    import com.omega.actor.BookSaveActor._
    
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
    override def save(book: Book): (Option[Book], Map[String, List[String]]) = {
        val id = book.id
        
        if(book.hasErrors) {
            (Option(book), Map("errors" -> book.errors, "messages" -> List()))
        } else {
            Try(bookDao.save(book)) match {
                case Success(savedBook) => id match {
                    case -1 => {
                        actorService.bookAction(BookCreated(savedBook.get.id, savedBook.get.name))
                        (savedBook, Map("errors" -> List(), "messages" -> List(s"Book ${savedBook.get.name} created successfully")))
                    }
                    case _ => {
                        actorService.bookAction(BookUpdated(savedBook.get.id, savedBook.get.name))
                        (savedBook, Map("errors" -> List(), "messages" -> List(s"Book ${savedBook.get.name} updated successfully")))
                    }
                }
                case Failure(fail) => {
                    println(fail); (Option(book), Map("errors" -> List(fail.toString), "messages" -> List()))
                }
            }    
        }
    }
}
