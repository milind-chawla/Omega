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
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(value = Array("/books"))
class BooksController extends CController with BeanLifeCycle {
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest): String = {
        s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model)(implicit req: HttpServletRequest): String = {
        model(this)
        
        model {
            Map[Any, Any]() + 
            ("books" -> {
                bookService.getBooks match {
            	    case Some(books) => books
            	    case None => new JArrayList[Book] 
            	}
            })
        }
        
    	s"$lname/index"
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
	def `new`(model: Model)(implicit req: HttpServletRequest): String = {
        model(this)
        
        model {
            Map[Any, Any]() + 
            ("book" -> Book())
        }
        
    	s"$lname/new"
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.POST))
	def create(@ModelAttribute book: Book, model: Model, redirectAttributes: RedirectAttributes)
        (implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.save(book) match {
            case Some(bk) if bk.id != -1 => {
                redirectAttributes.addFlashAttribute("message", s"Successfully created book: `${bk.name}`")
            }
            case _ => {
                redirectAttributes.addFlashAttribute("message", s"Error saving book: `${book.name}`")
                redirectAttributes.addFlashAttribute("book", book)
            }
        }
        
        s"redirect:/$lname/new"
    }
    
    @RequestMapping(value = Array("/{id}", "/{id}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("id") id: String, model: Model)(implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.getBook(id.longValue) match {
            case Some(book) => {
                model {
                    Map[Any, Any]() + 
                    ("book" -> book)
                }
                
                s"$lname/show"
            }
            case None => s"$lname/404"
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
