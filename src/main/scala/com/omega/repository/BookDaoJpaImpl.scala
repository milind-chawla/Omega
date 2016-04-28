package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book
import com.omega.util.BeanLifeCycle

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import scala.util.Try
import scala.util.Failure
import scala.util.Success

class BookDaoJpaImpl extends BookDao with BeanLifeCycle {
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def save(book: Book): Option[Book] = {
        Try(entityManager.persist(book)) match {
            case Success(_) => Option(book)
            case Failure(fail) => println(fail); Option(book)
        }
    }
    
    override def getBooks: Option[JList[Book]] = {
        Try(entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]) match {
            case Success(books) => Option(books)
            case Failure(fail) => println(fail); None
        }
    }
    
    override def getBook(id: Long): Option[Book] = {
        Try(entityManager.find(classOf[Book], id)) match {
            case Success(book) => Option(book)
            case Failure(fail) => println(fail); None
        }
    }
}
