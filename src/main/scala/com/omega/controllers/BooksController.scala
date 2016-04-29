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
import java.util.{ List => JList }
import com.omega.util.BeanLifeCycle
import com.omega.util.reflect.CController
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
@RequestMapping(value = Array("/books"))
class BooksController extends CController with BeanLifeCycle {
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest): String = {
        "redirect:/books/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model)(implicit req: HttpServletRequest): String = {
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
	def indexJ(implicit req: HttpServletRequest): JList[Book] = {
    	bookService.getBooks match {
    	    case Some(books) => books
    	    case None => new JArrayList[Book] 
    	}
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.GET))
    @ResponseBody
	def `new`(model: Model)(implicit req: HttpServletRequest): String = {
        model {
            Map[Any, Any]() + 
            ("book" -> Book())
        }
        
    	"books/new"
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.POST))
    @ResponseBody
	def create(@ModelAttribute book: Book, model: Model)(implicit req: HttpServletRequest): String = {
        model {
            Map[Any, Any]() + 
            ("book" -> book)
        }
        
    	"books/new"
    }
    
    @RequestMapping(value = Array("/{id}", "/{id}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("id") id: String, model: Model)(implicit req: HttpServletRequest): String = {
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
	def showJ(@PathVariable("id") id: String)(implicit req: HttpServletRequest): Book = {
        bookService.getBook(id.longValue) match {
            case Some(book) => book
            case None => Book()
        }
    }
}
