package com.tcs.configuration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractDispatcherServletInitializer {
	
	private static final String LOCATION = "/Users/Vibhor_Agarwal/Downloads/";
	 
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 25;//25MB
     
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30;//30MB
 
    private static final int FILE_SIZE_THRESHOLD = 0;

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		
		XmlWebApplicationContext servletContext = new XmlWebApplicationContext();
		servletContext.setConfigLocation("/WEB-INF/spring.xml");
		return servletContext;
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		
		XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
		rootContext.setConfigLocation("/WEB-INF/root-context.xml");
		return rootContext;
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		/*
		 * Used this method in order to register the <multipart-config> element to the DispatcherServlet.
		 */
		
		registration.setMultipartConfig(getMultipartConfigElement());
	}
	
	private MultipartConfigElement getMultipartConfigElement()
	{
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, 
				MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

}
