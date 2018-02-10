package com.thinkenterprise;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.thinkenterprise.controller.converter.KyroHttpMessageConverter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new KyroHttpMessageConverter());
		super.extendMessageConverters(converters);
	}

	
	
	
}
