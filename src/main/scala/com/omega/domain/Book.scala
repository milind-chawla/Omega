package com.omega.domain

import org.hibernate.validator.constraints.NotBlank

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GenerationType

import com.omega.util.OmegaHelpers.StringHelper
import javax.persistence.Column

@Entity
@Table(name = "BOOK")
class Book(_id: Long, _name: String) {
    
    def this(_name: String) = this(-1, _name)
    def this() = this(-1, null)
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = _id
    
    @Column
    var name: String = _name
    
    def setId(_id: Long): Unit = this.id = _id
    def getId(): Long = this.id
    
    def setName(_name: String): Unit = this.name = _name
    def getName(): String = this.name
    
    override def toString: String = "Book(id=" + id + ", name=" + name + ")"
    
    def hasErrors = errors.length > 0
    
    def errors = {
        var err = List.empty[String]
        
        if(getName().isEmpty()) {
            err = err :+ "'Name' cannot be empty"
        }
        
        err
    }
}

object Book {
    def apply(_id: Long, _name: String): Book = new Book(_id, _name)
    def apply(_name: String): Book = new Book(_name)
    def apply(): Book = new Book()
}
