package com.slokam.da.hc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class SampleInterceptor implements HandlerInterceptor{
   private Logger LOGGER = LoggerFactory.getLogger(getClass());
  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		LOGGER.trace("Entered into preHadle");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception {
			LOGGER.trace("Entered into postHandle");
			HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		}
	
	@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
		LOGGER.trace("Entered into afterCompletion");
			HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		}
	
}
