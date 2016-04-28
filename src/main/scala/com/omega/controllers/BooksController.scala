package com.omega.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import com.omega.service.BookService
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PathVariable
import scala.util.control.NonFatal
import com.omega.domain.Book

@Controller
@RequestMapping(value = Array("/books"))
class BooksController {
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/", ""), method = Array(RequestMethod.GET))
	def index = {
    	"books/index"
    }
    
    @RequestMapping(value = Array("/json"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def indexJ = {
    	bookService.getBooks
    }
    
    @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
	def show(@PathVariable("id") id: String) = {
        bookService.getBook(id.longValue) match {
            case Some(book) => "books/show"
            case None => "books/404"
        }
    }
    
    @RequestMapping(value = Array("/{id}/json"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def showJ(@PathVariable("id") id: String): Book = {
        bookService.getBook(id.longValue) match {
            case Some(book) => book
            case None => Book()
        }
    }
}
