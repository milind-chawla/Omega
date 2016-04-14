package com.omega.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import com.omega.util.BeanLifeCycle
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class OmegaSecurityConfig extends WebSecurityConfigurerAdapter with BeanLifeCycle {
    
    @Autowired
    @throws(classOf[Exception])
    def configureGlobal(auth: AuthenticationManagerBuilder): Unit = {
        auth.inMemoryAuthentication()
            .withUser("user")
                .password("password")
                .roles("USER")
            .and()
            .withUser("admin")
                .password("password")
                .roles("ADMIN", "USER")
    }
    
    @throws(classOf[Exception])
    override def configure(web: WebSecurity): Unit = {
        web
          .ignoring()
             .antMatchers("/resources/**");
    }
    
    @throws(classOf[Exception])
    override def configure(http: HttpSecurity): Unit = {
        http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
        .httpBasic()
        
    }
}