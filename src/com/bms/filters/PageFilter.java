package com.bms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bms.utils.common.ConstantsUtil;

/**
 * Servlet Filter implementation class APIFilter
 */
@WebFilter(filterName = "/APIFilter", urlPatterns = "/pages/*")
public class PageFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PageFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI().split("pages/")[1];
		System.out.println("reauest path:" + uri);
		System.out.println("session:" + session.getAttribute("pod-token"));
		
		
		if (session.getAttribute("pod-token") != null) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
