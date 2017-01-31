package com.jio.pe.app.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Import({ RepositoryConfig.class, com.jio.pe.app.security.WebSecurityConfig.class })
@Configuration
@EnableWebMvc
@EnableCaching
@EnableWebSecurity
@ComponentScan(basePackages = {  "com.jio.pe.app" })
public class AppConfig {

	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("properties/common.properties"));
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}
}