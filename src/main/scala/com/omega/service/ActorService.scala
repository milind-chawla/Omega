package com.omega.service

trait ActorService {
    import com.omega.actor.transport.BookTransport._
    
    def bookAction(action: BookAction): Unit
}