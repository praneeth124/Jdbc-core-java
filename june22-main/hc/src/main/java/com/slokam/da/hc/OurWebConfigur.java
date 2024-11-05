package com.slokam.da.hc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class OurWebConfigur extends WebMvcConfigurerAdapter {

	@Autowired
	private SampleInterceptor sampleInterceptor;
	

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(sampleInterceptor);
		super.addInterceptors(registry);
	}
	
}
