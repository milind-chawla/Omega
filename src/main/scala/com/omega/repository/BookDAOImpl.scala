package com.omega.repository

import java.util.{ ArrayList => JArrayList }
import java.util.{ List => JList }

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

import com.omega.domain.Book

import javax.persistence.Entity

@Repository
class BookDAOImpl extends BookDAO {
    
    @Autowired
    private var jdbcTemplate: JdbcTemplate = _
    
    def getBooks: JList[Book] = {
        jdbcTemplate.query("SELECT ID, NAME FROM BOOK", new BeanPropertyRowMapper(classOf[Book]))
    }
}
