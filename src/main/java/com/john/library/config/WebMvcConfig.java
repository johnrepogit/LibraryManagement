//package com.john.library.config;
//
//import java.util.Locale;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//	
//	@Bean
//	public LocaleResolver localeResolver() {
//	    SessionLocaleResolver slr = new SessionLocaleResolver();
//	    slr.setDefaultLocale(Locale.US);
//	    return slr;
//	}
//	
//	@Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//	
//	@Bean
//	public MessageSource messageSource() { 
//	    ResourceBundleMessageSource resource = 
//	            new ResourceBundleMessageSource();
//	    resource.setBasename("messages");
//	    resource.setDefaultEncoding("UTF-8");
//	    return resource;    
//	}
//    
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//}
