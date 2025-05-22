package com.capgemini.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Validator;

@Configuration
public class ValidationConfig {
	
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Load messages from messages.properties
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3600); // Cache for 1 hour
        return messageSource;
    }

   
    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }
	
	

}
