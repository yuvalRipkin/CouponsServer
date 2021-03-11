package com.yuval.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuval.coupons.dto.UserLoginData;
import com.yuval.coupons.logic.CacheController;

@Component
public class LoginFilter implements Filter {

	@Autowired
	private CacheController cacheController;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String pageRequested = httpRequest.getRequestURL().toString();

		// check if the required actions deserve a token(user who already logged in to
		// the site)
		if (pageRequested.endsWith("/loginDetails")) {
			chain.doFilter(request, response);
			return;
		}

		if (pageRequested.endsWith("/users") && httpRequest.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}
		if (pageRequested.endsWith("/companies") && httpRequest.getMethod().toString().equals("GET")) {
			chain.doFilter(request, response);
			return;
		}

		/*
		 * // Get the user's token from the http header and than use it to get the user
		 * // information from the cache "map". If the UserDate is not null, it means
		 * that the token exist, which means the user is logged in and he can do any
		 * action in the web-site.
		 */

		// String token = httpRequest.getParameter("Authorization");
		String token = httpRequest.getHeader("Authorization");
		UserLoginData userLoginData = (UserLoginData) cacheController.get(token);

		if (userLoginData != null) {
			// We take the user login data and put it "on" the request in order to use it at
			// the api where it necessary
			request.setAttribute("userLoginData", userLoginData);
			// The user successfully logged in, you can move to the next filter.
			chain.doFilter(request, response);
			return;
		}
		// If you get here it means the user have no token, which means he is not log
		// in successfully...
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(401);

	}

}
