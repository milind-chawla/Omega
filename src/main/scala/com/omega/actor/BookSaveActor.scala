package com.omega.actor

import akka.actor.Actor
import akka.actor.Props

class BookSaveActor extends Actor {
    import com.omega.util.OmegaHelpers._
    import BookSaveActor._
    
    def receive = {
        case BookCreated(id, name) =>
            (s"Book with id=$id, name=$name is created.").printSpecial
        case BookUpdated(id, name) =>
            (s"Book with id=$id, name=$name is updated.").printSpecial
        case _ =>
    }
}

object BookSaveActor {
    def props = Props[BookSaveActor]
    
    trait BookAction
    case class BookCreated(id: Long, name: String) extends BookAction
    case class BookUpdated(id: Long, name: String) extends BookAction
}
