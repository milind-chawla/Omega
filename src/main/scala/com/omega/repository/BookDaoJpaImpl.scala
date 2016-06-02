package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book
import com.omega.service.ActorService
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import com.omega.util.JavaList

class BookDaoJpaImpl(val actorService: ActorService) extends BookDao with BeanLifeCycle {
    import com.omega.actor.transport.BookTransport._
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def findById(id: Long): Option[Book] = {
        Option(entityManager.find(classOf[Book], id))
    }
    
    override def getBooks: JList[Book] = {
        entityManager
            .createQuery("SELECT b FROM Book b ORDER BY b.id ASC")
            .getResultList
            .asInstanceOf[JList[Book]]
    }
    
    override def getBooks(page: Int): JList[Book] = {
        val countQuery = entityManager.createQuery("SELECT count(b.id) FROM Book b")
        val countResult = countQuery.getSingleResult.asInstanceOf[Long]
        
        val pageSize = 10
        //val lastPageNumber = (countResult / pageSize) + 1
        
        val list = entityManager
            .createQuery("SELECT b FROM Book b ORDER BY b.id ASC")
            .setFirstResult((page - 1)* pageSize)
            .setMaxResults(pageSize)
            .getResultList
            .asInstanceOf[JList[Book]]
        
        list
    }
    
    override def save(book: Book): Option[Book] = {
        entityManager.persist(book)
        
        actorService.bookAction(BookCreated(book.id, book.name))
        
        Option(book)
    }
    
    override def update(book: Book): Option[Book] = {
        val id = book.id
        
        val book2 = entityManager.find(classOf[Book], id)
        
        book2.setName(book.name)
        entityManager.persist(book2)
        
        actorService.bookAction(BookUpdated(book2.id, book2.name))
        
        Option(book2)
    }
}
