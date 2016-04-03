package com.omega.service

import java.util.{ List => JList }
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Service
import com.omega.domain.Book
import com.omega.repository.BookDAO
import javax.annotation.Resource
import javax.persistence.Entity
import javax.persistence.Table
import com.omega.util.BeanLifeCycle

@Service("BookService")
@DependsOn(Array("BookDAO", "OmegaServiceConfig"))
class BookServiceImpl extends BookService with BeanLifeCycle {
    
    @Resource
    private var bookDAO: BookDAO = _
    
    def getBooks: JList[Book] = {
        bookDAO.getBooks
    }
}
