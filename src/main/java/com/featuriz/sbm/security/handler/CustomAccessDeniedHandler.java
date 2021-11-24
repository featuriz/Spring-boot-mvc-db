/**
 * 
 */
package com.featuriz.sbm.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 22-Nov-20219:56:16 pm
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			logger.warn("User: {} attempted to access the protected URL: {}", auth.getName(), request.getRequestURI());
		}

		response.sendRedirect(request.getContextPath() + "/accessDenied");

	}

}
