package com.magicalcyber.blog.spring.web.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Logger logger = LoggerFactory
			.getLogger(WebInitializer.class);

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		logger.debug("getServletFilters");

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);

		// HiddenHttpMethodFilter will help you get around the problem, but you
		// have to include a hidden field _method=PUT in your form. If you use
		// the spring:form taglib this will be done automatically for you, but
		// your example seems to use plain HTML.
		//
		// Reference: http://stackoverflow.com/a/13651928
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();

		// From Spring Framework Documents: HiddenHttpMethodFilter
		// NOTE: This filter needs to run after multipart processing in case of
		// a multipart POST request, due to its inherent need for checking a
		// POST body parameter.So typically, put a Spring MultipartFilter before
		// this HiddenHttpMethodFilter in your web.xml filter chain.

		return new Filter[] { hiddenHttpMethodFilter, characterEncodingFilter };
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		logger.debug("onStartup");
		super.onStartup(servletContext);// MUST HAVE
		servletContext.setInitParameter("defaultHtmlEscape", "true");
	}
}
