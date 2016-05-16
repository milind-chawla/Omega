package com.omega.service

import com.omega.util.BeanLifeCycle
import akka.actor.ActorSystem
import com.omega.actor.BookActorLocal

class ActorServiceImpl(val actorSystem: ActorSystem) extends ActorService with BeanLifeCycle {
    import com.omega.actor.transport.BookTransport._
    
    /* akka:tcp://OmegaActorSystemRemote@127.0.0.1:5150/user/bookActorLocal */
    val bookActorLocal = actorSystem.actorOf(BookActorLocal.props, "bookActorLocal");
    
    def bookAction(action: BookAction): Unit = {
        bookActorLocal ! action
    }
}
