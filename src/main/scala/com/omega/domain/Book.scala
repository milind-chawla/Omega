package com.omega.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import org.hibernate.validator.constraints.NotEmpty

@Entity
class Book(_name: String) {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: BigInt = _
    
    @NotEmpty
    var name: String = _name
    
    def setId(_id: BigInt): Unit = this.id = _id
    def getId(): BigInt = this.id
    
    def setName(_name: String): Unit = this.name = _name
    def getName(): String = this.name
}