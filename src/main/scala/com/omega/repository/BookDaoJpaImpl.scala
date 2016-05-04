package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import scala.util.control.NonFatal

class BookDaoJpaImpl extends BookDao with BeanLifeCycle {
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def getBooks: Option[JList[Book]] = {
        Option(entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]])
    }
    
    override def getBook(id: Long): Option[Book] = {
        Option(entityManager.find(classOf[Book], id))
    }
    
    override def save(book: Book): Option[Book] = {
        entityManager.persist(book)
        Option(book)
    }
    
    def update(book: Book): Option[Book] = {
        val id = book.id
        
        None
    }
}
