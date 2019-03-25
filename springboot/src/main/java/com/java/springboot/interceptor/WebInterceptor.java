package com.java.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.java.springboot.util.Const;

/**
 * web拦截器,自定义拦截器
 * @author tzh
 *
 */
public class WebInterceptor implements HandlerInterceptor {
	
	private static Logger log = LoggerFactory.getLogger(WebInterceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}
	
	/**
	 * 请求发生后执行
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}
	/**
	 * 
	 * 请求发生前执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		log.info("拦截器拦截请求>>>>>>"+request.getRequestURI());
		HttpSession session=request.getSession(true);
		Object obj=session.getAttribute(Const.ATTR);
		if(obj!=null&&Const.USER.equals(obj)){
			return true;
		}
		response.sendRedirect(request.getContextPath()+"/login");
		//request.getRequestDispatcher("").forward(request, response);
		return false;
	}

}

