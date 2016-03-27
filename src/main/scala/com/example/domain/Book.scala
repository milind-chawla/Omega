package com.example.domain

import java.io.Serializable

import java.util.ArrayList
import java.util.Date
import java.util.List

import javax.persistence.CascadeType
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

import javax.validation.Valid

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

@Entity
@Table(name = "BOOK")
class Book(_name: String) extends Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: BigInt = _

	var name: String = _name

	def setId(id: BigInt): Unit = this.id = id
	def getId: BigInt = this.id

	def setName(name: String): Unit = this.name = name
	def getName: String = this.name
}
