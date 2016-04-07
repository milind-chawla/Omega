package com.omega.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity

@Configuration
@EnableWebSecurity
class OmegaSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    @throws(classOf[Exception])
    def configureGlobal(auth: AuthenticationManagerBuilder): Unit = {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password("password")
            .roles("USER")
    }
    
    @throws(classOf[Exception])
    override def configure(http: HttpSecurity): Unit = {
        
    }
}