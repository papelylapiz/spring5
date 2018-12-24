package com.escodigo.introduccion_spring_mvc_config.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ing. Engel Diaz
 */

@EnableWebMvc
@Configuration
@ComponentScan({ "com.escodigo.introduccion_spring_mvc_config.controller" })
public class WebConfiguration implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// mapping '/' to index view name without a controller
		ViewControllerRegistration r = registry.addViewController("/");
		r.setViewName("index");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for CSS and JS
		registry.addResourceHandler("/themes/**").addResourceLocations("/resources/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

		// Register resource handler for images
		registry.addResourceHandler("/images/**").addResourceLocations("/resouces/images/")
				.setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	}

	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
}
