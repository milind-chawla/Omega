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

class BookDaoJpaImpl(val actorService: ActorService) extends BookDao with BeanLifeCycle {
    import com.omega.actor.BookSaveActor._
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def getBooks: Option[JList[Book]] = {
        Option(entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]])
    }
    
    override def getBook(id: Long): Option[Book] = {
        Option(entityManager.find(classOf[Book], id))
    }
    
    override def save(book: Book): (Option[Book], Map[String, List[String]]) = {
        val id = book.id
        
        if(book.hasErrors) {
            (Option(book), Map("errors" -> book.errors, "messages" -> List()))
        } else {
            entityManager.persist(book)
            
            book.id match {
                case x if x > 0 => {
                    actorService.bookAction(BookCreated(book.id, book.name))
                    (Some(book), Map("errors" -> List(), "messages" -> List(s"Book ${book.name} created successfully")))                    
                }
                case _ => (Option(book), Map("errors" -> List(s"Book ${book.name} cannot be created"), "messages" -> List()))
            }
        }
    }
    
    def update(book: Book): Option[Book] = {
        val id = book.id
        
        None
    }
}
