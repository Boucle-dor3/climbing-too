package com.oc.climbingtoo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class ClimbingtooApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimbingtooApplication.class, args);
	}

	@Autowired
	private ThymeleafProperties properties;

	@Value("${spring.thymeleaf.templates_root:}")
	private String templatesRoot;


	@Bean
	public ITemplateResolver defaultTemplateResolver() {
		FileTemplateResolver resolver = new FileTemplateResolver();
		resolver.setSuffix(properties.getSuffix());
		resolver.setPrefix(templatesRoot);
		resolver.setTemplateMode(properties.getMode());
		resolver.setCacheable(properties.isCache());
		return resolver;
	}
}





