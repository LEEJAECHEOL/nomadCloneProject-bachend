package com.cos.oauth2jwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Value("${file.path}")
	private String uploadFolder;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry
		.addResourceHandler("/uploads/**")		// url 패턴 : /upload/파일명 을 낚아챔
		.addResourceLocations("file:///" + uploadFolder)		//실제 물리적인 경로
		.setCachePeriod(60*6*10)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
		
	}

}
