package com.omega.controllers

import java.util.Date
import scala.util.control.NonFatal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import com.omega.debug.Debug.debug
import com.omega.domain.Book
import com.omega.service.BookService
import com.omega.util.BeanLifeCycle
import com.omega.util.OmegaHelpers._
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HomeController extends BeanLifeCycle {
    
    @Autowired
    private var bookService: BookService = _
    
    @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def home(model: Model) = {
    	"redirect:/home/index"
    }
    
    @RequestMapping(value = Array("/home/index"), method = Array(RequestMethod.GET))
	def index(model: Model) = {
    	model.addAttribute("date", new Date)
    	
    	val book = Book("Book " + (95 + scala.util.Random.nextInt(26)).toChar)
    	
    	noexec {
    	    debug.on("before saving: " + book)
    	    
        	try {
                bookService.save(book)    
            } catch {
                case NonFatal(ex) => {
                    println(ex)
                }
            }
            
            debug.on("after saving: " + book)
    	}
    	
        exec {
        	try {
                model.addAttribute("books", bookService.getBooks)    
            } catch {
                case NonFatal(ex) => {
                    println(ex)
                }
            }
        }
    	
    	"home/index"
    }
}
