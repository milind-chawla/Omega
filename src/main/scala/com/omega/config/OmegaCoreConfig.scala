package com.omega.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean

@Configuration
class OmegaCoreConfig {
    
    @Bean 
    def getApplicationName: String = {
        println("********* getApplicationName ***************")
        "Omega"
    }
}