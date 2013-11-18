package com.magicalcyber.blog.spring.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
/**
 * Contains middle-tier services, data sources, etc.
 */
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.magicalcyber.blog.spring.service" })
public class RootConfig {
 
}

