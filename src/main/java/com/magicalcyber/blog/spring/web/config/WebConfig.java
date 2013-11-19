package com.magicalcyber.blog.spring.web.config;

import java.util.Locale;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Contains controllers, view resolvers, locale resolvers, and other web-related
 * beans.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.magicalcyber.blog.spring.web" })
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(WebConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("addResourceHandlers");
		registry.addResourceHandler("/resources/").addResourceLocations(
				"/resources/**");
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		logger.debug("configureDefaultServletHandling");
		// if the spring dispatcher is mapped to / then forward non handled
		// requests
		// (e.g. static resource) to the container's "default servlet"
		configurer.enable();
	}

	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		logger.debug("addInterceptors");
		// Default name of the locale specification parameter: "locale".
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		registry.addInterceptor(localeChangeInterceptor);
	}

	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		logger.debug("simpleMappingExceptionResolver");

		SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
		mappings.put("org.springframework.dao.DataAccessException",
				"dataAccessFailure");
		mappings.put("org.springframework.transaction.TransactionException",
				"dataAccessFailure");
		b.setExceptionMappings(mappings);

		return b;
	}

	@Bean
	public ViewResolver viewResolver() {
		logger.debug("viewResolver");

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	//
	// @Bean
	// CommonsMultipartResolver filterMultipartResolver() {
	// return new CommonsMultipartResolver();
	// }

	@Bean
	public MessageSource messageSource() {
		logger.debug("messageSource");

		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages");
		messageSource.setCacheSeconds(5);
		messageSource.setDefaultEncoding("UTF-8");

		return messageSource;
	}

	@Bean
	LocalValidatorFactoryBean validator() {
		logger.debug("begin init validator");
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());
		return validator;
	}

	@Bean
	public LocaleResolver localeResolver() {
		logger.debug("localeResolver");

		SessionLocaleResolver lr = new org.springframework.web.servlet.i18n.SessionLocaleResolver();
		lr.setDefaultLocale(Locale.ENGLISH);

		return lr;
	}
}
