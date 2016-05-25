package com.omega.controllers

import java.util.{ ArrayList => JArrayList }
import java.util.{ List => JList }

import scala.util.control.NonFatal

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.support.RedirectAttributes

import com.omega.domain.Book
import com.omega.service.BookService
import com.omega.util.BeanLifeCycle

import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMethod
import com.omega.util.JavaList
import com.omega.exceptions.JSONException
import com.omega.exceptions.BookNotFoundException
import javax.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping(value = Array("/books"))
class BooksController extends CController {
    import CControllerHelpers._
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(req: HttpServletRequest) = Action {
        s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(req: HttpServletRequest, 
	        @RequestParam(value="page", required=false) _page: String) = Action(this) { mv =>
	    val page = if(_page.intValue < 0) {
	        1
	    } else {
	        _page.intValue
	    }
	    
	    val books = bookService.getBooks(page)
	    
	    val disable = if(page == 1) {
	        "prev"
	    } else if(books.size() < 5) {
	        "next"
	    }
	    
	    mv.addObject("disable", disable)
        mv.addObject("page", page)
        mv.addObject("books", books)
        
        mv.setViewName(s"$lname/index")
        mv
    }
    
    @RequestMapping(value = Array("/index.json", "/index.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def indexJ(req: HttpServletRequest): JList[Book] = {
        try {
            bookService.getBooks  
        } catch {
          case NonFatal(e) =>
              throw new JSONException(new Exception(e.getMessage))
        }
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.GET))
	def `new`(req: HttpServletRequest) = Action(this) { mv =>
        mv.addObject("book", Book())
        mv.setViewName(s"$lname/new")
        mv
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.POST))
	def `new_post`(@Valid book: Book, result: BindingResult, redirectAttributes: RedirectAttributes) = Action(this) { mv =>
        if(result.hasErrors) {
            mv.addObject("book", book)
            mv.setViewName(s"$lname/new")
        } else {
            bookService.save(book) 
            redirectAttributes.addFlashAttribute("messages", JavaList(s"$book created successfully"))
            mv.setForRedirect()
            mv.setViewName(s"redirect:/$lname/${book.id}") 
        }
        mv
    }
    
    @RequestMapping(value = Array("/{bid}", "/{bid}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("bid") bid: String, redirectAttributes: RedirectAttributes) = Action(this) { mv =>
        bookService.findById(bid.longValue) match {
            case Some(book) => {
                mv.addObject("book", book)
                mv.setViewName(s"$lname/show")
                mv
            }
            case None => throw new BookNotFoundException(bid)
        }
    }
    
    @RequestMapping(value = Array("/{bid}.json", "/{bid}.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def showJ(@PathVariable("bid") bid: String): Book = {
        try {
            bookService.findById(bid.longValue) match {
                case Some(book) => book
                case None => throw new JSONException(new Exception(s"Book with id `$bid` not found"))
            }  
        } catch {
          case NonFatal(e) =>
              throw new JSONException(new Exception(e.getMessage))
        }
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.GET))
	def edit(@PathVariable("bid") bid: String, redirectAttributes: RedirectAttributes) = Action(this) { mv =>
        bookService.findById(bid.longValue) match {
            case Some(book) => {
                mv.addObject("book", book)
                mv.setViewName(s"$lname/edit")
                mv
            }
            case None => throw new BookNotFoundException(bid)
        }
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.POST))
	def edit_post(@PathVariable("bid") bid: String, @Valid book: Book, result: BindingResult, redirectAttributes: RedirectAttributes) = Action(this) { mv =>
        if(result.hasErrors) {
            mv.addObject("book", book)
            mv.setViewName(s"$lname/edit")
        } else {
            bookService.update(book)
            redirectAttributes.addFlashAttribute("warnings", JavaList(s"$book updated successfully"))
            mv.setForRedirect()
            mv.setViewName(s"redirect:/$lname/${book.id}")
        }
        mv
    }
}
