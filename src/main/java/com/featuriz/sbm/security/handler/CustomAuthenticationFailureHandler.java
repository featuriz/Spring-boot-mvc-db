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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 22-Nov-20219:44:29 pm
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger logger = LogManager.getLogger();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		final String refererUrl = request.getHeader("Referer");
		logger.info("Authentication Failure: {}", refererUrl);
		System.out.println(refererUrl);

//		String jsonPayload = "{\"message\" : \"%s\", \"timestamp\" : \"%s\" }";
//		response.getOutputStream()
//				.println(String.format(jsonPayload, exception.getMessage(), Calendar.getInstance().getTime()));
		super.onAuthenticationFailure(request, response, exception);
	}

}
