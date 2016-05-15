package com.omega.repository

import java.util.{ List => JList }

import com.omega.actor.BookSaveActor.BookCreated
import com.omega.domain.Book
import com.omega.service.ActorService
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table
import com.omega.util.JavaList

class BookDaoJpaImpl(val actorService: ActorService) extends BookDao with BeanLifeCycle {
    import com.omega.actor.BookSaveActor._
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def getBooks: JList[Book] = {
        entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
    }
    
    override def findById(id: Long): Option[Book] = {
        Option(entityManager.find(classOf[Book], id))
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
