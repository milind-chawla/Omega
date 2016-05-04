package com.omega.service

trait ActorService {
    import com.omega.actor.BookSaveActor._
    
    def bookAction(action: BookAction): Unit
}