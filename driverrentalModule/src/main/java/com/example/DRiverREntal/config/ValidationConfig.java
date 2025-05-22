package com.example.DRiverREntal.config;

import org.springframework.context.MessageSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
 
import jakarta.validation.Validator;
//Marks this class as a configuration class for Spring
@Configuration
public class ValidationConfig {
	// Defines a MessageSource bean to load validation messages from properties file
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Load messages from messages.properties
        messageSource.setDefaultEncoding("UTF-8");// Set character encoding to UTF-8
        messageSource.setCacheSeconds(3600); // Cache for 1 hour
        return messageSource;
    }
	 // Defines a Validator bean using the custom MessageSource for validation messages
   
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());// Use custom message source
        return validatorFactoryBean;// Return the validator bean
    }
 
	
 
}



