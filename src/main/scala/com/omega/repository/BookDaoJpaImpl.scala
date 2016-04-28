package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table

class BookDaoJpaImpl extends BookDao with BeanLifeCycle {
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def save(book: Book): Option[Book] = {
        entityManager.persist(book)
        Option(book)
    }
    
    override def getBooks: Option[JList[Book]] = {
        Option(entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]])
    }
    
    override def getBook(id: Long): Option[Book] = {
        Option(entityManager.find(classOf[Book], id))
    }
}
