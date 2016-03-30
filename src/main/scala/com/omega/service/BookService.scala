package com.omega.service

import java.util.{List => JList}

import com.omega.domain.Book

trait BookService {
    def getBooks: JList[Book]
}