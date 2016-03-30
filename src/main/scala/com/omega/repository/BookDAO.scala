package com.omega.repository

import java.util.{List => JList}

import com.omega.domain.Book

trait BookDAO {
    def getBooks: JList[Book]
}