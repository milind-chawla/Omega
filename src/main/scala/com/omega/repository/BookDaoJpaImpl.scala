package com.omega.repository

import java.util.{ List => JList }
import com.omega.domain.Book
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import com.omega.util.BeanLifeCycle

class BookDaoJpaImpl extends BookDao with BeanLifeCycle {
    
    @PersistenceContext
    private var entityManager: EntityManager = _
    
    def getBooks: JList[Book] = {
        entityManager.createNativeQuery("INSERT INTO BOOK(ID, NAME) VALUES(10, 'Book 10')").executeUpdate()
        entityManager.createNativeQuery("INSERT INTO BOOK(ID, NAME) VALUES(11, 'Book 11')").executeUpdate()
        entityManager.createNativeQuery("INSERT INTO BOOK(ID, NAME) VALUES(1, 'Book 1')").executeUpdate()
        
        val books = entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
        books
    }
}
