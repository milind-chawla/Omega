package com.omega.repository

import java.util.{ List => JList }

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import com.omega.domain.Book
import com.omega.util.BeanLifeCycle

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.Table

@Repository("BookDAO")
@Transactional
class BookDAOImpl extends BookDAO with BeanLifeCycle {
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @PersistenceContext
    private var entityManager: EntityManager = _
    
    @Transactional
    def getBooks: JList[Book] = {
        println(jdbcTemplate.getExceptionTranslator)
        
        jdbcTemplate.execute("INSERT INTO BOOK(ID, NAME) VALUES(1, 'Book 1')")
        
        /*val b = Book(1, "Book " + scala.util.Random.nextInt(100))
        entityManager.persist(b)*/
        
        val books = entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
        books
    }
}
