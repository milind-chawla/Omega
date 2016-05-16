package com.omega.actor

import akka.actor.Actor
import akka.actor.Props

class BookActorLocal extends Actor {
    import com.omega.util.OmegaHelpers._
    import com.omega.actor.transport.BookTransport._
    
    override def receive = {
        case b @ BookCreated(_, _) => {
            val bookActorRemote = context.actorSelection("akka.tcp://OmegaActorSystemRemote@127.0.0.1:5151/user/bookActorRemote")
            bookActorRemote ! b
        }
        
        case b @ BookUpdated(_, _) => {
            val bookActorRemote = context.actorSelection("akka.tcp://OmegaActorSystemRemote@127.0.0.1:5151/user/bookActorRemote")
            bookActorRemote ! b
        }
        case x => {
            val bookActorRemote = context.actorSelection("akka.tcp://OmegaActorSystemRemote@127.0.0.1:5151/user/bookActorRemote")
            bookActorRemote ! x
        }
    }
}

object BookActorLocal {
    def props = Props[BookActorLocal]
}
