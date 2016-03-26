package com.example.controllers

import java.util.Date

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Controller

import org.springframework.ui.Model

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HelloWorldController {

	@RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
	def index (model: Model) = {
    	model.addAttribute("date", new Date)
    	"index"
	}
}
