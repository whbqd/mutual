package com.dy.config;

import com.dy.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: ZT
 * Date: 2020/5/22 23:19
 * Version 1.0
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/view/*");
        registration.setName("LogCostFilter");
        registration.setOrder(1);
        return registration;
    }
}
