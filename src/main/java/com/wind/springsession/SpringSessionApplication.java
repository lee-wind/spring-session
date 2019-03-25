package com.wind.springsession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wind.springsession.config.XSSStringJsonSerializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.MultipartConfigElement;

@EnableRedisHttpSession
@SpringBootApplication
@MapperScan("com.wind.springsession.dao")
@ServletComponentScan("com.wind.springsession.filter")
public class SpringSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSessionApplication.class, args);
	}

	@Bean
	@Primary
	public ObjectMapper XSSObjectMapper(Jackson2ObjectMapperBuilder builder){


		ObjectMapper objectMapper = builder.createXmlMapper(false).build();

		SimpleModule xssModel = new SimpleModule("XSSStringJsonSerializer");
		xssModel.addSerializer(new XSSStringJsonSerializer());
		objectMapper.registerModule(xssModel);

		return objectMapper;
	}
//
//	@Bean
//	public MultipartConfigElement multipartConfigElement(){
//
//		MultipartConfigFactory factory = new MultipartConfigFactory();
//		factory.setMaxFileSize("2048000KB");
//		factory.setMaxRequestSize("2048000KB");
//		return factory.createMultipartConfig();
//	}
}
