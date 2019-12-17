package com.myclass.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.myclass")
public class WebMvcConfig implements WebMvcConfigurer {

	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/api/**")//map đến những url của api
		.allowedOrigins("*")//chỉ định những domain đc phép truy cập api(ví dụ:"myclass.vn" thì chỉ có domain myclass.vn đc truy cập)
		.allowedMethods("GET","POST","PUT","DELETE")//chỉ định những phương thức đc truy cập
		.allowCredentials(false)//chỉ định cookie có đc sử dụng hay ko
		.maxAge(4800);//set thời gian request đc lưu trong cache,mặc định là 1800(30 phút)
	}
	

}
