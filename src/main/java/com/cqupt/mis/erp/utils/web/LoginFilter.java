package com.cqupt.mis.erp.utils.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse rep,FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		HttpServletResponse response = (HttpServletResponse) rep;

		// 判断后台是否登陆，如果没登陆跳转到index.jsp
		if (session.getAttribute("userUnique") == null || "".equals((String)session.getAttribute("userUnique"))) {

		//	System.out.println("用户未登陆，跳转到index.jsp");
			
			response.sendRedirect(request.getContextPath());
			return;
		}
		chain.doFilter(req, rep); // 如果登陆直接向下执行
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	public void destroy() {
	}
	
}
