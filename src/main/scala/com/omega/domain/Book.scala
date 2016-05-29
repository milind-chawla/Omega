package com.omega.domain

import org.hibernate.validator.constraints.NotBlank

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.GenerationType

import com.omega.util.OmegaHelpers.StringHelper
import javax.persistence.Column
import com.omega.util.JavaList
import javax.validation.constraints.Size
import com.omega.io.IO
import com.omega.io.IO
import org.apache.commons.io.FileUtils
import org.springframework.security.crypto.codec.Base64



@Entity
@Table(name = "BOOK")
class Book(_id: Long, _name: String, _image: String) {
    
    def this(_name: String, _image: String) = this(-1, _name, _image)
    def this() = this(-1, null, null)
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = _id
    
    @Column
    @Size(min=3, max=20)
    var name: String = _name
    
    @Column
    var image: String = _
    
    def setId(_id: Long): Unit = this.id = _id
    def getId(): Long = this.id
    
    def setName(_name: String): Unit = this.name = _name
    def getName(): String = this.name
    
    def setImage(_image: String): Unit = this.image = _image
    def getImage(): String = this.image
    
    def getImageHex(): String = {
        import java.io.File
        import com.omega.io.IO._
        
        new File(IO.uploadDir, getImage()).readBase64
    }
    
    override def toString: String = s"Book(id=$id, name=$name, image=$image)"
}

object Book {
    def apply(_id: Long, _name: String, _image: String): Book = new Book(_id, _name, _image)
    def apply(_name: String, _image: String): Book = new Book(_name, _image)
    def apply(): Book = new Book()
}
