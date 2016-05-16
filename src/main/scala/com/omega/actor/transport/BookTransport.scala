package com.omega.actor.transport

object BookTransport {
    trait BookAction
    
    case class BookCreated(id: Long, name: String) extends BookAction
    case class BookUpdated(id: Long, name: String) extends BookAction
}
