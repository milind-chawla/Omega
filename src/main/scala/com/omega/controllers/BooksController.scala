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
import org.springframework.validation.BindingResult
import javax.validation.Valid

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
	def `new_post`(@ModelAttribute book: Book, model: Model, redirectAttributes: RedirectAttributes)
        (implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.save(book) match {
            case (Some(bk), map) => {
                val messages = new JArrayList[String]
                val errors = new JArrayList[String]
                
                map("messages") foreach { message =>
                    messages.add(message)
                }
                
                map("errors") foreach { error =>
                    errors.add(error)
                }
                
                redirectAttributes.addFlashAttribute("messages", messages)
                redirectAttributes.addFlashAttribute("errors", errors)
            }
            case _ => {
                ("*** Something Wierd ***").printSpecial
            }
        }    
        
        s"redirect:/$lname/new"
    }
    
    @RequestMapping(value = Array("/{bid}", "/{bid}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("bid") bid: String, model: Model)(implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.getBook(bid.longValue) match {
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
    
    @RequestMapping(value = Array("/{bid}.json", "/{bid}.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def showJ(@PathVariable("bid") bid: String)(implicit req: HttpServletRequest): Book = {
        bookService.getBook(bid.longValue) match {
            case Some(book) => book
            case None => Book()
        }
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.GET))
	def edit(@PathVariable("bid") bid: String, model: Model)(implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.getBook(bid.longValue) match {
            case Some(book) => {
                model {
                    Map[Any, Any]() + 
                    ("book" -> book)
                }
                
                s"$lname/edit"
            }
            case None => s"$lname/404"
        }
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.POST))
	def edit_post(@PathVariable("bid") bid: String, @ModelAttribute book: Book, model: Model, redirectAttributes: RedirectAttributes)
        (implicit req: HttpServletRequest): String = {
        model(this)
        
        bookService.save(book) match {
            case (Some(bk), map) => {
                val messages = new JArrayList[String]
                val errors = new JArrayList[String]
                
                map("messages") foreach { message =>
                    messages.add(message)
                }
                
                map("errors") foreach { error =>
                    errors.add(error)
                }
                
                errors.size match {
                    case x if x > 0 => {
                        redirectAttributes.addFlashAttribute("errors", errors)
                        
                        s"redirect:/$lname/$bid/edit"
                    }
                    case _ => {
                        redirectAttributes.addFlashAttribute("messages", messages)
                        
                        s"redirect:/$lname/$bid"                        
                    }
                }
            }
            case _ => {
                ("*** Something Wierd ***").printSpecial
                
                s"redirect:/$lname/$bid/edit"
            }
        }
    }
}
