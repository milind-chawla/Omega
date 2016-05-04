package com.omega.service

import com.omega.util.BeanLifeCycle
import akka.actor.ActorSystem

class ActorServiceImpl(val actorSystem: ActorSystem) extends ActorService with BeanLifeCycle {
    
}
