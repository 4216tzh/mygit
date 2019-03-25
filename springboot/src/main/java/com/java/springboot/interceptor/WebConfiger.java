package com.java.springboot.interceptor;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 声明此类为一个配置类
 * @author tzh
 *
 */
@Configuration
public class WebConfiger extends WebMvcConfigurerAdapter{

	/**
	 * 添加拦截器，以及指定需要拦截的路径
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new WebInterceptor()).addPathPatterns("/fri/**")
				.addPathPatterns("/pay/**");
	}
	
	
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController( "/" ).setViewName( "forward:/nologin.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
	}



	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;

	}
	
	

}
