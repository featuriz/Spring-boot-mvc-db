package com.featuriz.sbm.security.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import com.featuriz.sbm.entity.User;
//import com.featuriz.sbm.repository.UserRepository;
import com.featuriz.sbm.service.UserService;

/**
 * @author Sudhakar Krishnan <featuriz@gmail.com>
 * @Copyright 2009 - 2021 Featuriz
 * @DateTime 25-Nov-2021 10:41:21 pm
 */
@Component
public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger logger = LogManager.getLogger();
	private boolean postOnly = true;

//  Both UserRepository, UserService are same
//	@Autowired
//	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	public SimpleAuthenticationFilter() {
		super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/perform_login", "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		logger.info("Starting Custom Authentication!");
		if (this.postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		// If the requirement is more filed ::
		// https://www.baeldung.com/spring-security-extra-login-fields
		// https://github.com/eugenp/tutorials/tree/master/spring-security-modules/spring-5-security/src/main/java/com/baeldung/loginextrafieldssimple
		String username = request.getParameter("user_name");
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";

		// If its not here, throws error for not-found-username. This is a filter.
//		User user = userRepository.findByUserName(username);
		User user = userService.findUserByUserName(username);

		if (user == null) {
			try {
				response.sendRedirect(request.getContextPath() + "/login?error=true");
				return null;
			} catch (IOException e) {
				logger.error("Redirection Exception!");
			}
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
