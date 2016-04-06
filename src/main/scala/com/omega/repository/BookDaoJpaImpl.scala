package com.omega.repository

import java.util.{ List => JList }

import com.omega.domain.Book
import com.omega.util.BeanLifeCycle

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class BookDaoJpaImpl extends BookDao with BeanLifeCycle {
    
    @PersistenceContext(unitName = "OmegaUnit1")
    private var entityManager: EntityManager = _
    
    override def save(book: Book): Book = {
        entityManager.persist(book)
        book
    }
    
    override def getBooks: JList[Book] = {
        // entityManager.createNativeQuery("INSERT INTO BOOK(NAME) VALUES('Book N')").executeUpdate()
        val books = entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
        books
    }
}
