package com.bms.filters;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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

import com.bms.commom.domain.ApiResultEntity;
import com.bms.user.entities.UserEntity;
import com.bms.user.services.impl.UserServiceImpl;
import com.bms.utils.common.ConstantsUtil;
import com.bms.utils.common.DateFormatUtil;
import com.bms.utils.common.StringUtil;
import com.bms.utils.json.IgnoreNullProprety;
import com.bms.utils.redis.RedisUtil;
import com.sun.corba.se.impl.corba.RequestImpl;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 使用注解标注过滤器
 * 
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器 属性filterName声明过滤器的名称,可选
 *                                                属性urlPatterns指定要过滤
 *                                                的URL模式,也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 *                                                urlPatterns="/*" 表示过滤掉所有请求
 */
@WebFilter(filterName = "ApiFilter", urlPatterns = "/api/v1.0/*")
public class ApiFilter implements Filter {
	
	private UserServiceImpl userService = new UserServiceImpl();
	private StringUtil stringUtil = new StringUtil();

	@Override
	public void destroy() {

		System.out.println("过滤器销毁");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		System.out.println("session:" + session.getAttribute("pod-token"));		
		
		if (session.getAttribute("pod-token") != null && session != null) {
			chain.doFilter(request, response);
		} else {
			System.out.println(httpRequest.getContextPath());
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("过滤器初始化");

	}
}