package com.omega.service

import com.omega.domain.Book
import org.springframework.stereotype.Service
import org.springframework.stereotype.Component

@Service
class BookServiceImpl extends BookService {
    
    def getBooks: List[Book] = {
        val books = List(Book(1, "Alice In Wonder Land"), Book(2, "Bugs Bunny"))
        books
    }
}
