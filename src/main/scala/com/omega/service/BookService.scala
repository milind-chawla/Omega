package com.omega.service

import com.omega.domain.Book

trait BookService {
    def getBooks: List[Book]
}