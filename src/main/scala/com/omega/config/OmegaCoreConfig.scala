package com.omega.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = Array("com.omega.service"))
class OmegaCoreConfig {
    
    @Bean 
    def getApplicationName: String = {
        println("********* getApplicationName ***************")
        "Omega"
    }
}
