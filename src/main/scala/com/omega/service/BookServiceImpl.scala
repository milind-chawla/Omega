package com.omega.service

import java.util.{List => JList}

import org.springframework.stereotype.Service

import com.omega.domain.Book
import com.omega.repository.BookDAO

import javax.annotation.Resource
import javax.persistence.Entity

@Service
class BookServiceImpl extends BookService {
    
    @Resource
    private var bookDAO: BookDAO = _
    
    def getBooks: JList[Book] = {
        bookDAO.getBooks
    }
}
