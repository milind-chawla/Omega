package com.omega.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Table

@Entity
@Table(name = "BOOK")
class Book(_id: Long, _name: String) {
    
    def this(_name: String) = this(-1, _name)
    def this() = this(-1, null)
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = _id
    
    @NotEmpty
    var name: String = _name
    
    def setId(_id: Long): Unit = this.id = _id
    def getId(): Long = this.id
    
    def setName(_name: String): Unit = this.name = _name
    def getName(): String = this.name
}

object Book {
    def apply(_id: Long, _name: String): Book = new Book(_id, _name)
    def apply(_name: String): Book = new Book(_name)
}
