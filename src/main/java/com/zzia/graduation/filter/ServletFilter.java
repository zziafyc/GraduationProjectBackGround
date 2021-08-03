package com.zzia.graduation.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String target = request.getRequestURI();
		target = target.substring(target.lastIndexOf('/') + 1, target.length());
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (this.includes.contains(target)) {
			RequestDispatcher rds = request.getRequestDispatcher(target);
			rds.forward(arg0, response);
		} else {
			arg2.doFilter(arg0, response);
		}
	}

	private ArrayList<String> includes = new ArrayList<String>();

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.includes.addAll(Arrays.asList(arg0.getInitParameter(
				"includeServlets").split(",")));
	}

}
