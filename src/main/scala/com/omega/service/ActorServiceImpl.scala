package com.omega.service

import com.omega.util.BeanLifeCycle
import akka.actor.ActorSystem
import com.omega.actor.BookSaveActor

class ActorServiceImpl(val actorSystem: ActorSystem) extends ActorService with BeanLifeCycle {
    import com.omega.actor.BookSaveActor._
    
    val bookSaveActor = actorSystem.actorOf(BookSaveActor.props, "bookSaveActor");
    
    def bookAction(action: BookAction): Unit = {
        bookSaveActor ! action
    }
}
