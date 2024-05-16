package com.develhope.spring.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    FilterRegistrationBean<AmministratoreFilter> filterRegistrationBean(JWTUtils jwtUtils) {
        FilterRegistrationBean<AmministratoreFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AmministratoreFilter(jwtUtils));
        filterRegistrationBean.addUrlPatterns("/amministratore/*");
        return filterRegistrationBean;
    }

}
