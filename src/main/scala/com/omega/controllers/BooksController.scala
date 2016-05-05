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

@Controller
@RequestMapping(value = Array("/books"))
class BooksController extends CController with BeanLifeCycle {
    import scala.collection.JavaConversions._
    import com.omega.util.OmegaHelpers._
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("", "/"), method = Array(RequestMethod.GET))
	def root(model: Model)(implicit req: HttpServletRequest): String = {
        s"redirect:/$lname/index"
    }
    
    @RequestMapping(value = Array("/index", "/index/"), method = Array(RequestMethod.GET))
	def index(model: Model, redirectAttributes: RedirectAttributes)(implicit req: HttpServletRequest): String = {
        model(this)
        
        try {
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
        } catch {
            case NonFatal(e) => {
                model {
                    Map[Any, Any]() +
                    ("messages" -> List().toJavaList) + 
                    ("errors" -> List("Unknown error").toJavaList)
                }
                
                s"$lname/err"
            }
        }
    }
    
    @RequestMapping(value = Array("/index.json", "/index.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def indexJ(implicit req: HttpServletRequest): JList[Book] = {
        try {
            bookService.getBooks match {
        	    case Some(books) => books
        	    case None => new JArrayList[Book] 
        	}    
        } catch {
            case NonFatal(e) => {
                new JArrayList[Book]
            }
        }
    }
    
    @RequestMapping(value = Array("/new", "/new/"), method = Array(RequestMethod.GET))
	def `new`(model: Model, redirectAttributes: RedirectAttributes)(implicit req: HttpServletRequest): String = {
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
        
        try {
            val (Some(bk), map) = bookService.save(book)
            
            redirectAttributes.addFlashAttribute("messages", map("messages").toJavaList)
            redirectAttributes.addFlashAttribute("errors", map("errors").toJavaList)
            
            bk.id match {
                case x if x > 0 => s"redirect:/$lname/${x}"
                case _ => s"redirect:/$lname/new"
            }
        } catch {
            case NonFatal(e) => {
                redirectAttributes.addFlashAttribute("messages", List().toJavaList)
                redirectAttributes.addFlashAttribute("errors", List("Unknown error").toJavaList)
            
                s"redirect:/$lname/new"
            }
        }
    }
    
    @RequestMapping(value = Array("/{bid}", "/{bid}/"), method = Array(RequestMethod.GET))
	def show(@PathVariable("bid") bid: String, model: Model, redirectAttributes: RedirectAttributes)(implicit req: HttpServletRequest): String = {
        model(this)
        
        try {
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
        } catch {
            case NonFatal(e) => {
                model {
                    Map[Any, Any]() +
                    ("messages" -> List().toJavaList) + 
                    ("errors" -> List("Unknown error").toJavaList)
                }
                
                s"$lname/err"
            }
        }
    }
    
    @RequestMapping(value = Array("/{bid}.json", "/{bid}.json/"), method = Array(RequestMethod.GET), produces = Array("application/json; charset=UTF-8"))
    @ResponseBody
	def showJ(@PathVariable("bid") bid: String)(implicit req: HttpServletRequest): Book = {
        try {
            bookService.getBook(bid.longValue) match {
                case Some(book) => book
                case None => Book()
            }    
        } catch {
            case NonFatal(e) => {
                Book()
            }
        }
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.GET))
	def edit(@PathVariable("bid") bid: String, model: Model, redirectAttributes: RedirectAttributes)(implicit req: HttpServletRequest): String = {
        model(this)
        
        try {
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
        } catch {
            case NonFatal(e) => {
                model {
                    Map[Any, Any]() +
                    ("messages" -> List().toJavaList) + 
                    ("errors" -> List("Unknown error").toJavaList)
                }
                
                s"$lname/err"
            }
        }
        
    }
    
    @RequestMapping(value = Array("/{bid}/edit", "/{bid}/edit/"), method = Array(RequestMethod.POST))
	def edit_post(@PathVariable("bid") bid: String, @ModelAttribute book: Book, model: Model, redirectAttributes: RedirectAttributes)
        (implicit req: HttpServletRequest): String = {
        model(this)
        
        try {
            val (Some(bk), map) = bookService.update(book)
            
            val messages = map("messages").toJavaList
            val errors = map("errors").toJavaList
            
            redirectAttributes.addFlashAttribute("messages", messages)
            redirectAttributes.addFlashAttribute("errors", errors)
            
            bk.id match {
                case x if x > 0 => {
                    errors.size match {
                        case y if y > 0 => s"redirect:/$lname/${x}/edit"
                        case _ => s"redirect:/$lname/${x}"
                    }
                }
                case _ => s"redirect:/$lname/404"
            }
        } catch {
            case NonFatal(e) => {
                redirectAttributes.addFlashAttribute("messages", List().toJavaList)
                redirectAttributes.addFlashAttribute("errors", List("Unknown error").toJavaList)
            
                s"redirect:/$lname/${bid}/edit"
            }
        }
    }
}
