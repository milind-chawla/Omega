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

import java.util.{ ArrayList => JArrayList }

@Controller
@RequestMapping(value = Array("/books"))
class BooksController {
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model) = {
        "redirect:/books/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
        model {
            Map[Any, Any]() + 
            ("books" -> {
                bookService.getBooks match {
            	    case Some(books) => books
            	    case None => new JArrayList[Book] 
            	}
            })
        }
        
    	"books/index"
    }
    
    @RequestMapping(value = Array("/index.json", "/index.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def indexJ = {
    	bookService.getBooks match {
    	    case Some(books) => books
    	    case None => new JArrayList[Book] 
    	}
    }
    
    @RequestMapping(value = Array("/{id}", "/{id}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("id") id: String, model: Model) = {
        bookService.getBook(id.longValue) match {
            case Some(book) => {
                model {
                    Map[Any, Any]() + 
                    ("book" -> book)
                }
                
                "books/show"
            }
            case None => "books/404"
        }
    }
    
    @RequestMapping(value = Array("/{id}.json", "/{id}.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def showJ(@PathVariable("id") id: String): Book = {
        bookService.getBook(id.longValue) match {
            case Some(book) => book
            case None => Book()
        }
    }
}
