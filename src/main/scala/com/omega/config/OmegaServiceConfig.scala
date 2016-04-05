package com.omega.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ComponentScan
import com.omega.util.BeanLifeCycle
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration("OmegaServiceConfig")
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.omega.service"))
class OmegaServiceConfig extends BeanLifeCycle {
}
