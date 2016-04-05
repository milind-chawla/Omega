package com.omega.service

import java.util.{ List => JList }
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.omega.domain.Book
import com.omega.repository.BookDAO
import com.omega.util.BeanLifeCycle
import javax.annotation.Resource
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import scala.xml.dtd.REQUIRED

@Service("BookService")
@Transactional
class BookServiceImpl extends BookService with BeanLifeCycle {
    
    @Resource
    private var bookDAO: BookDAO = _
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @PersistenceContext
    private var entityManager: EntityManager = _
    
    /*@Transactional
    def getBooks: JList[Book] = {
        bookDAO.getBooks
    }*/
    
    @Transactional
    def getBooks: JList[Book] = {
        /*println(jdbcTemplate.getExceptionTranslator)
        jdbcTemplate.execute("INSERT INTO BOOK(ID, NAME) VALUES(1, 'Book 1')")*/
        
        entityManager.createNativeQuery("INSERT INTO BOOK(ID, NAME) VALUES(1, 'Book 1')").executeUpdate()
        
        val books = entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
        books
    }
}
