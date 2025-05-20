package com.uniq.JWTpractice.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import com.uniq.JWTpractice.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String auth = request.getHeader("authorization");

		if ((auth == null) || !(jwtUtils.verify(auth))) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("application/json");
			response.getWriter().write("Unauthorized or invalid token");
			return false;
		}

		return true;
	}

}
