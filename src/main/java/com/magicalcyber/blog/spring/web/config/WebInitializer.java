package com.magicalcyber.blog.spring.web.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
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

		return new Filter[] { characterEncodingFilter,
				new HiddenHttpMethodFilter() };
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		logger.debug("==================== onStartup");
		super.onStartup(servletContext);// MUST HAVE
		servletContext.setInitParameter("defaultHtmlEscape", "true");
	}
}
