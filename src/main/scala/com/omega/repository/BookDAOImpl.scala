package com.omega.repository

import java.util.{ List => JList }
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import com.omega.domain.Book
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import com.omega.config.BeanLifeCycle

@Repository("BookDAO")
@Transactional
@DependsOn(Array("OmegaCoreConfig"))
class BookDAOImpl extends BookDAO with BeanLifeCycle {
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @PersistenceContext
    private var entityManager: EntityManager = _
    
    @Transactional
    def getBooks: JList[Book] = {
        jdbcTemplate.query("SELECT ID, NAME FROM BOOK", new BeanPropertyRowMapper(classOf[Book]))
        entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
    }
}
