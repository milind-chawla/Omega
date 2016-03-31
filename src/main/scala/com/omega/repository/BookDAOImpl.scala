package com.omega.repository

import java.util.{ List => JList }

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import com.omega.domain.Book

import javax.persistence.Entity
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
@DependsOn(Array("OmegaCoreConfig"))
class BookDAOImpl extends BookDAO {
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    @PersistenceContext
    private var entityManager: EntityManager = _ 
    
    def getBooks: JList[Book] = {
        //jdbcTemplate.query("SELECT ID, NAME FROM BOOK", new BeanPropertyRowMapper(classOf[Book]))
        entityManager.createQuery("SELECT b FROM Book b").getResultList.asInstanceOf[JList[Book]]
    }
}
