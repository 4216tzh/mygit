package com.java.springboot.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 * @author tzh
 *
 */
@WebFilter(filterName="webMvcFilter",urlPatterns={"/*"})
public class WebMvcFilter implements Filter{

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器初始化");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("过滤器拦截请求...");
		HttpServletRequest req=(HttpServletRequest)request;
		//HttpServletResponse rep=(HttpServletResponse)response;
		String encode=req.getCharacterEncoding();
		String url=req.getRequestURL().toString();
		System.out.println("请求路径:::::"+url);
		System.out.println("编码方式:::"+encode);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}

}
