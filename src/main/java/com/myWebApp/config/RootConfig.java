package com.myWebApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author: lpc
 * @Description:
 * @Date: create in 14:30 2017/10/13
 * @Modified By:
 */
@Configuration
@ComponentScan(
        basePackages = {"com"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)}
)
public class RootConfig {
}
